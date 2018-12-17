package com.coreteka.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class PersistenceUtil {

    private static EntityManagerFactory emFactory = null;
    private static ThreadLocal<EntityManager> threadLocalEntityManager = new ThreadLocal<>();
    private static ThreadLocal<EntityTransaction> threadLocalTransaction = new ThreadLocal<>();
    private static ThreadLocal<String> threadLocalCaller = new ThreadLocal<>();



    public static EntityManager getEntityManager() {
        if (threadLocalEntityManager.get() == null || !threadLocalEntityManager.get().isOpen()) {
            threadLocalEntityManager.set(getEntityManagerFactory().createEntityManager());
        }
        return threadLocalEntityManager.get();
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
        if (threadLocalCaller.get() == null) {
            threadLocalCaller.set(Thread.currentThread().getStackTrace()[3].toString());
            if (threadLocalTransaction.get() == null || !threadLocalTransaction.get().isActive())
                threadLocalTransaction.set(getEntityManager().getTransaction());
            threadLocalTransaction.get().begin();
        }
    }


    public static void commitTransaction() {

        String committingCaller = (Thread.currentThread().getStackTrace()[3].toString());

        if (committingCaller.equals(threadLocalCaller.get())){
            threadLocalTransaction.get().commit();
            threadLocalEntityManager.get().close();
            threadLocalCaller.set(null);
        }
    }
}