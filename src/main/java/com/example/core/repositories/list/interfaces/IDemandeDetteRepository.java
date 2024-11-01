package com.example.core.repositories.list.interfaces;

import java.util.List;

import com.example.core.data.entities.DemandeDette;
import com.example.core.repository.IRepository;

public interface IDemandeDetteRepository extends IRepository<DemandeDette> {
    DemandeDette DemandeById(int id);

    public List<DemandeDette> findDemandesByClientId(int id);
}