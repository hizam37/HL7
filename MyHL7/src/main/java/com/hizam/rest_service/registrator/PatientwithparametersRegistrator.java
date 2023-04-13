package com.hizam.rest_service.registrator;

import com.hizam.rest_service.entity.Parameters;



import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.logging.Logger;


@Stateless
public class PatientwithparametersRegistrator {

    @Inject
    private Logger logger;

    @Inject
    private EntityManager entityManager;


    public Parameters create(Parameters item) {
        logger.info("Patient's data is created: " + item);
        entityManager.persist(item);
        return item;
    }

    public Parameters delete(long id) {
        Parameters item = entityManager.find(Parameters.class,id);
        if(item!=null)
        {
            logger.info("Deleted User by id: " + id);
            entityManager.remove(item);
        }
            logger.info("User does not exist");
        return item;
    }
}