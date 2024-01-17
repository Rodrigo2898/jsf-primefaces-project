package com.jsfproject.jsfproject.repository;

import com.jsfproject.jsfproject.entity.Empresa;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class SchemaGeneration {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("JsfPrime");

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        List<Empresa> empresaList = entityManager.createQuery("from Empresa", Empresa.class).getResultList();

        System.out.println(empresaList);

        entityManager.close();
        entityManagerFactory.close();
    }
}
