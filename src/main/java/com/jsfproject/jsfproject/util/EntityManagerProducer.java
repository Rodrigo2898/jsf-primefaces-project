package com.jsfproject.jsfproject.util;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

@ApplicationScoped
public class EntityManagerProducer {
    private EntityManagerFactory factory;

    public EntityManagerProducer() {
        this.factory = Persistence.createEntityManagerFactory("JsfPrimeface");
    }

    @Produces
    @RequestScoped
    public EntityManager createEntityManage() {
        return this.factory.createEntityManager();
    }

    public void closeEntityManger(@Disposes EntityManager manager) {
        manager.close();
    }
}
