package com.jsfproject.jsfproject.repository;

import com.jsfproject.jsfproject.entity.RamoAtivitdade;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.io.Serializable;
import java.util.List;

public class RamoAtividadeRepository implements Serializable {
    private static final long serialVesionUUID = 1L;

    private EntityManager entityManager;

    public RamoAtividadeRepository() {
    }

    public RamoAtividadeRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<RamoAtivitdade> pesquisar(String descricao) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery<RamoAtivitdade> criteriaQuery = criteriaBuilder.createQuery(RamoAtivitdade.class);
        Root<RamoAtivitdade> root = criteriaQuery.from(RamoAtivitdade.class);
        criteriaQuery.select(root);
        criteriaQuery.where(criteriaBuilder.like(root.get("descricao"), descricao + "%"));

        TypedQuery<RamoAtivitdade> query = entityManager.createQuery(criteriaQuery);

        return query.getResultList();
    }
}
