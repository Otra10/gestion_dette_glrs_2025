package com.example.views.Interfaces;

import com.example.core.data.entities.Dette;
import com.example.core.data.entities.User;

public interface IDetteView extends IView<Dette> {
    void AfficherDetteNonSolder();
    void listerDettes(User user);
    
}
