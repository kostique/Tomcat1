package com.coreteka.util;

import com.coreteka.exceptions.NullUserAttributeValueException;

import javax.persistence.*;
import javax.validation.ConstraintViolationException;

public class PersistenceUtil {

    private static EntityManagerFactory emFactory = null;
    private static EntityManager entityManager = null;
    private static EntityTransaction transaction = null;
    private static String caller;



    public static EntityManager getEntityManager() {
        if (entityManager == null || !entityManager.isOpen()) {
            entityManager = getEntityManagerFactory().createEntityManager();
        }
        return entityManager;
    }


    private static EntityManagerFactory getEntityManagerFactory() {
        if (emFactory == null) {
            emFactory = Persistence.createEntityManagerFactory("NewPersistenceUnit");
        }
        return emFactory;
    }


    public static void close() {
        emFactory.close();
    }


    public static void beginTransaction() {
        if (caller == null) {
            caller = Thread.currentThread().getStackTrace()[3].toString();
            if (transaction == null || !transaction.isActive())
                transaction = getEntityManager().getTransaction();
            transaction.begin();
        }
    }


    public static void commitTransaction() {

        String committingCaller = (Thread.currentThread().getStackTrace()[3].toString());
        if (committingCaller.equals(caller)){
            transaction.commit();
            entityManager.close();
            caller = null;
        }
    }

    public static void rollbackTransaction(){
        transaction.rollback();
        entityManager.close();
        caller = null;
    }
}