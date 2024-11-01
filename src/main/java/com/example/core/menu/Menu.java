package com.example.core.menu;

import java.util.Scanner;

import com.example.core.ConfigLoader;
import com.example.core.data.entities.User;
import com.example.core.factory.implement.FactoryRepository;
// import com.example.core.factory.implement.FactoryRepositoryDb;
// import com.example.core.factory.implement.FactoryRepositoryJpa;
import com.example.core.factory.implement.FactoryService;
import com.example.core.factory.implement.FactoryServiceDb;
import com.example.core.factory.implement.FactoryServiceJpa;
import com.example.core.factory.implement.FactoryView;
import com.example.core.session.SessionManager;
import com.example.views.Interfaces.IArticleView;
import com.example.views.Interfaces.IClientView;
import com.example.views.Interfaces.IDemandeDetteView;
import com.example.views.Interfaces.IDetteView;
import com.example.views.Interfaces.IPaiementView;
import com.example.views.Interfaces.IUserView;

public class Menu {

    public Menu() {
    }

    static FactoryRepository factoryRepository;
    // static FactoryRepositoryDb factoryRepositoryDb;
    // static FactoryRepositoryJpa factoryRepositoryJpa;
    static FactoryService factoryService;
    static FactoryServiceDb factoryServiceDb;
    static FactoryServiceJpa factoryServiceJpa;
    static FactoryView factoryView;

    static IClientView clientView;
    static IArticleView articleView;
    static IUserView userView;
    static IDetteView detteView;
    static IPaiementView paiementView;
    static IDemandeDetteView demandeDetteView;

    private static User user = SessionManager.getCurrentUser();

    static {
        initializeFactories();
        initializeViews();
    }

    private static void initializeFactories() {
        // Charger le type de repository depuis ConfigLoader
        String repositoryType = ConfigLoader.getRepositoryType();

        // Instancier les factories en fonction du type de repository
        switch (repositoryType.toLowerCase()) {
            // case "jpa":
            //     factoryRepositoryJpa = new FactoryRepositoryJpa();
            //     factoryServiceJpa = new FactoryServiceJpa(factoryRepositoryJpa);
            //     factoryView = new FactoryView(factoryServiceJpa, factoryRepositoryJpa);
            //     break;
            // case "jdbc":
            //     factoryRepositoryDb = new FactoryRepositoryDb();
            //     factoryServiceDb = new FactoryServiceDb(factoryRepositoryDb);
            //     factoryView = new FactoryView(factoryServiceDb, factoryRepositoryDb);
            //     break;
            case "list":
                factoryRepository = new FactoryRepository();
                factoryService = new FactoryService(factoryRepository);
                factoryView = new FactoryView(factoryService, factoryRepository);
                break;
            default:
                throw new IllegalArgumentException("Type de repository non reconnu: " + repositoryType);
        }
    }

    private static void initializeViews() {
        clientView = factoryView.getInstanceClientView();
        articleView = factoryView.getInstanceArticleView();
        userView = factoryView.getInstanceUserView();
        detteView = factoryView.getInstanceDette();
        paiementView = factoryView.getInstancePaiementView();
        demandeDetteView = factoryView.getInstanceDemandeDetteView();
    }

    // Méthode pour afficher le menu selon le rôle de l'utilisateur
    public static void afficherMenu(User user) {
        Scanner scanner = new Scanner(System.in);
        switch (user.getRole()) {
            case BOUTIQUIER:
                afficherMenuBoutiquier();
                break;
            case ADMIN:
                afficherMenuAdmin();
                break;
            case CLIENT:
                afficherMenuClient();
                break;
            default:
                System.out.println("Rôle inconnu");
        }
    }

    // Menu pour Boutiquier
    public static void afficherMenuBoutiquier() {
        Scanner scanner = new Scanner(System.in);
        int choix;
        do {
            System.out.println("Menu Boutiquier :");
            System.out.println("1. Créer un client");
            System.out.println("2. Lister les clients");
            System.out.println("3. Rechercher un client par téléphone");
            System.out.println("4. Créer une dette");
            System.out.println("5. Lister les dettes non soldées d'un client");
            System.out.println("6. Enregistrer un paiement pour une dette");
            System.out.println("7. Lister les demandes de dette en cours");
            System.out.println("8. Associer un compte à un client");
            System.out.println("9. Quitter");

            System.out.print("Entrez votre choix : ");
            choix = scanner.nextInt();
            scanner.nextLine(); // Pour consommer le saut de ligne

            // Appeler les actions selon le choix de l'utilisateur
            demanderActionBoutiquier(choix);

        } while (choix != 9); // Continue tant que l'utilisateur ne choisit pas de quitter

    }

    // Menu pour Admin
    public static void afficherMenuAdmin() {
        Scanner scanner = new Scanner(System.in);
        int choix;
        do {
            System.out.println("Menu Admin :");
            System.out.println("1. Créer un compte utilisateur");
            System.out.println("2. Lister les comptes utilisateurs");
            System.out.println("3. Lister les articles");
            System.out.println("4. Créer un articles");
            System.out.println("5. Quitter");

            System.out.print("Entrez votre choix : ");
            choix = scanner.nextInt();
            scanner.nextLine();

            demanderActionAdmin(choix);

        } while (choix != 5);

    }

    // Menu pour Client
    public static void afficherMenuClient() {
        Scanner scanner = new Scanner(System.in);
        int choix;
        do {
            System.out.println("Menu Client :");
            System.out.println("1. Lister vos dettes non soldées");
            System.out.println("2. Faire une demande de dette");
            System.out.println("3. Lister vos demandes de dette");
            System.out.println("4. Quitter");

            System.out.print("Entrez votre choix : ");
            choix = scanner.nextInt();
            scanner.nextLine();

            demanderActionClient(choix);

        } while (choix != 4);

    }

    // Gérer les actions du Boutiquier
    public static void demanderActionBoutiquier(int choix) {
        switch (choix) {
            case 1:
                clientView.ajout();
                break;
            case 2:
                clientView.lister();
                break;
            case 3:
                clientView.rechercherParTelephone();
                break;
            case 4:
                detteView.ajout();
                break;
            case 5:
                detteView.AfficherDetteNonSolder();
                break;
            case 6:
                paiementView.ajout();
                break;
            case 7:
                // detteView.listerDemandesAvecFiltre();
                break;
            case 8:
                clientView.associerCompte();
                break;
            case 9:
                System.out.println("Au revoir!");
                break;
            default:
                System.out.println("Choix invalide. Veuillez réessayer.");
        }

    }

    // Gérer les actions de l'Admin
    public static void demanderActionAdmin(int choix) {
        switch (choix) {
            case 1:
                userView.ajout();
                break;
            case 2:
                userView.lister();
                break;
            case 3:
                articleView.lister();
                break;
            case 4:
                articleView.ajout();
                break;
            case 5:
                System.out.println("Au revoir !");
                break;
            default:
                System.out.println("Option non valide.");
        }
    }

    // Gérer les actions du Client
    public static void demanderActionClient(int choix) {
        switch (choix) {
            case 1:
                detteView.listerDettes(user);
                break;
            case 2:
                demandeDetteView.ajout();
                break;
            case 3:
                demandeDetteView.afficherDemandesPourUtilisateur(user);
                break;
            case 5:
                System.out.println("Au revoir !");
                break;
            default:
                System.out.println("Option non valide.");
        }
    }
}
