package com.example.core.authentification;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.example.core.ConfigLoader;
import com.example.core.data.Enum.RoleUser;
import com.example.core.data.entities.User;
import com.example.core.repositories.list.UserRepository;
import com.example.core.session.SessionManager;

public class Authentification {

   // Simuler une base de données d'utilisateurs en mémoire
    private static List<User> users = new ArrayList<>();
    static UserRepository listUserRepository = new UserRepository();
    // static UserRepositoryDb userRepositoryDb = new UserRepositoryDb();
    // static UserRepositoryJpa userRepository = new UserRepositoryJpa(); 
    static String repositoryType = ConfigLoader.getRepositoryType();

    static {
        // Ajouter des users pour l'exemple en mémoire
        users.add(new User("Jean", "boutique", "123", RoleUser.BOUTIQUIER));
        users.add(new User("Admin", "admin", "123", RoleUser.ADMIN));
        users.add(new User("Marie", "client", "123", RoleUser.CLIENT));
    }

    // Méthode pour demander la connexion
    public static User connexion() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Veuillez entrer votre login :");
        String login = sc.nextLine();

        System.out.println("Veuillez entrer votre mot de passe :");
        String password = sc.nextLine();

        switch (repositoryType.toLowerCase()) {
            case "list":
                // Utilisation du repository en mémoire
                for (User user : users) {
                    if (user.getLogin().equals(login) && user.getPassword().equals(password)) {
                        System.out.println("Connexion réussie !");
                        SessionManager.login(user);
                        System.out.println(SessionManager.getCurrentUser());
                        return user;
                    }
                }
                break;

            // case "jpa":
            //     // Utilisation du repository JPA
            //     User user = userRepository.findByLoginAndPassword(login, password);
            //     if (user != null) {
            //         System.out.println("Connexion réussie !");
            //         SessionManager.login(user);
            //         System.out.println(SessionManager.getCurrentUser());
            //         return user;
            //     }
            //     break;

            // case "jdbc":
            //     // Utilisation du repository JPA
            //     User userdb = userRepositoryDb.findByLoginAndPassword(login, password);
            //     if (userdb != null) {
            //         System.out.println("Connexion réussie !");
            //         SessionManager.login(userdb);
            //         System.out.println(SessionManager.getCurrentUser());
            //         return userdb;
            //     }
            //     break;

            default:
                throw new AssertionError("Type de repository inconnu : " + repositoryType);
        }

        // Si aucun utilisateur n'est trouvé
        System.out.println("Échec de la connexion, veuillez vérifier vos identifiants.");
        return null;
    }
}
