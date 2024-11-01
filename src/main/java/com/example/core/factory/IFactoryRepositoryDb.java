package com.example.core.factory;

import com.example.core.repositories.list.interfaces.IArticleRepository;
import com.example.core.repositories.list.interfaces.IClientRepository;
import com.example.core.repositories.list.interfaces.IDetteRepository;
import com.example.core.repositories.list.interfaces.IPaiementRepository;
import com.example.core.repositories.list.interfaces.IUserRepository;


public interface IFactoryRepositoryDb {
    IArticleRepository getInstanceArticleRepositoryDb();
    IClientRepository getInstanceClientRepositoryDb();
    IDetteRepository getInstanceDetteRepositoryDb();
    IUserRepository getInstanceUserRepositoryDb();
    IPaiementRepository getInstacePaiementRepositoryDb();
    
}
