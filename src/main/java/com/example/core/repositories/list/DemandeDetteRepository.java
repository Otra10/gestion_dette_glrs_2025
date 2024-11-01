package com.example.core.repositories.list;

import java.util.ArrayList;
import java.util.List;

import com.example.core.data.entities.DemandeDette;
import com.example.core.repositories.list.interfaces.IDemandeDetteRepository;
import com.example.core.repository.impl.RepositoryList;

public class DemandeDetteRepository extends RepositoryList<DemandeDette> implements IDemandeDetteRepository {

    @Override
    public DemandeDette DemandeById(int id) {
        for (DemandeDette demandeDette : list) {
            if (demandeDette.getId()==id){
                return demandeDette;
            }
        }
        return null;
    }

    @Override
    public List<DemandeDette> findDemandesByClientId(int clientId) {
        List<DemandeDette> listes = new ArrayList<>();
        for (DemandeDette demandeDette : list) {
            if(demandeDette.getClient().getId()==clientId){
                listes.add(demandeDette);
            }
        }

        return listes;
    }
        

    
    
}
