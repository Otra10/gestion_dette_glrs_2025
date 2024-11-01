package com.example.views;

import java.util.List;
import java.util.Scanner;

import com.example.core.data.entities.Client;
import com.example.core.data.entities.DemandeDette;
import com.example.core.data.entities.User;
import com.example.core.services.interfaces.IClientService;
import com.example.core.services.interfaces.IUserService;
import com.example.views.Interfaces.IClientView;

public class ClientView extends View<Client> implements IClientView{
    
    private Scanner scanner=new Scanner(System.in);
   
    private DemandeDette demandeDette = new DemandeDette();
    private IClientService clientService;
    private IUserService userService ;

    public ClientView(IClientService clientService,IUserService userService){
        this.clientService = clientService;
        this.userService = userService;
    }

    @Override
    public void lister() {
        List<Client> clients = this.clientService.getAll();

        if (clients.isEmpty()) {
            System.out.println("Aucun client n'est disponible.");
        } else {
            System.out.println("Liste des clients :");
            for (Client client : clients) {
                System.out.println(client);
            }
        }
    }

    @Override
    public void  ajout(){
      
       
        System.out.println("Renseigner le surname : ");

        String surname = scanner.nextLine();
        
        
        System.out.println("Renseigner le telephone : ");

        String telephone = scanner.nextLine();
     
        
        System.out.println("Renseigner l'adresse : ");

        String address = scanner.nextLine();

        Client client=new Client(surname, telephone, address,null,null);

        clientService.store(client);
   

    }

    @Override
    public void rechercherParTelephone() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Entrez le numéro de téléphone du client : ");
        String telephone = scanner.nextLine();

        // Recherche du client dans la base de données par téléphone
        Client client = clientService.ClientByPhone(telephone);

        if (client != null) {
            System.out.println("Client trouvé :");
            System.out.println("Nom : " + client.getSurname());
            System.out.println("Téléphone : " + client.getTelephone());
            System.out.println("Adresse : " + client.getAddress());
            // Afficher d'autres détails si nécessaire
        } else {
            System.out.println("Aucun client trouvé avec ce numéro de téléphone.");
        }
    }

    @Override
    public void associerCompte(){
        System.out.println("Saisissez l'id du client");
        int clientId = scanner.nextInt();
        Client client = clientService.findClientById(clientId);
        System.out.println("Saisissez l'id du user que vous voulez associer au client");
        User user = userService.userFindById(clientId);

        client.setUserAccount(user);
        
        
    }

    @Override
    protected List<Client> getAll() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void AjoutDette() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void AfficherDemandeDette() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
