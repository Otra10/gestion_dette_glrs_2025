package com.example.views.Interfaces;

import com.example.core.data.entities.User;

public interface IDemandeDetteView{
    void listerDemandeDetteEncours();
    void ajout();
    void afficherDemandesPourUtilisateur(User user);
}
