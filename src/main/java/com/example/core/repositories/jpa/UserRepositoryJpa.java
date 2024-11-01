package com.example.core.repositories.jpa;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.example.core.data.entities.User;
import com.example.core.repositories.list.interfaces.IUserRepository;
import com.example.core.repository.impl.RepositoryJpa;

public class UserRepositoryJpa extends  RepositoryJpa<User> implements IUserRepository{

    public UserRepositoryJpa() {
        super(User.class);
        //TODO Auto-generated constructor stub
    }

    
    @Override
    public User findByLoginAndPassword(String login, String password) {
        EntityManager em = entityManagerFactory.createEntityManager();
        try {
            TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.login = :login AND u.password = :password", User.class);
            query.setParameter("login", login);
            query.setParameter("password", password);
            List<User> results = query.getResultList();
            return results.isEmpty() ? null : results.get(0);
        } finally {
            em.close();
        }
    }

    @Override
    public User findByLogin(String login) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByLogin'");
    }

}
