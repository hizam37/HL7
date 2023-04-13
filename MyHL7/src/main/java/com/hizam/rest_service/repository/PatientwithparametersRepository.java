package com.hizam.rest_service.repository;

import com.hizam.rest_service.entity.Parameters;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;
import java.util.logging.Logger;


@ApplicationScoped
public class PatientwithparametersRepository {

    @Inject
    private Logger logger;

    @Inject
    private EntityManager entityManager;

    public Parameters getById(long id) {
        logger.info("Get User by id: " + id);
        return entityManager.find(Parameters.class, id);
    }

    public List<Parameters> getAll() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Parameters> criteria = criteriaBuilder.createQuery(Parameters.class);
        criteria.from(Parameters.class);
        return entityManager.createQuery(criteria).getResultList();
    }
}

