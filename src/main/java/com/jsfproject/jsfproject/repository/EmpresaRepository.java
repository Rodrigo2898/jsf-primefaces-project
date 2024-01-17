package com.jsfproject.jsfproject.repository;

import com.jsfproject.jsfproject.entity.Empresa;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

import java.io.Serializable;
import java.util.List;

public class EmpresaRepository implements Serializable {
    private static final long serialVesionUUID = 1L;

    private EntityManager entityManager;

    public EmpresaRepository() {
    }

    public EmpresaRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Empresa porId(Long id) {
        return entityManager.find(Empresa.class, id);
    }

    public List<Empresa> pesquisar(String nome) {
        String jpql = "from Empresa where nomeFantasia like :nomeFantasia";
        TypedQuery<Empresa> query = entityManager
                .createQuery(jpql, Empresa.class);
        query.setParameter("nomeFantasia", nome + "%");
        return query.getResultList();
    }

    public Empresa guardar(Empresa empresa) {
        return entityManager.merge(empresa);
    }

    public void remover(Empresa empresa) {
        empresa = porId(empresa.getId());
        entityManager.remove(empresa);
    }
}
