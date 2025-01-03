package com.github.jslblar080.repository;

import com.github.jslblar080.BaseEntity;
import com.github.jslblar080.BaseRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;

import java.util.List;

public class JpaRepository implements BaseRepository {

    private final EntityManagerFactory emf;

    private EntityManager em;

    private EntityTransaction txn;

    public JpaRepository(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public void save(BaseEntity entity) {

        em = emf.createEntityManager();
        txn = em.getTransaction();

        txn.begin();
        try {
            em.persist(entity);
            em.flush();

            txn.commit();
        } catch (RuntimeException e) {
            if (txn.isActive()) txn.rollback();
            throw e;
        } finally {
            if (em.isOpen()) em.close();
        }
        em.close();
    }

    @Override
    public <T> List<T> getResultList(String sql, Class<T> tClass) {

        em = emf.createEntityManager();
        txn = em.getTransaction();

        List<T> resultList;

        txn.begin();
        try {
            resultList = em.createNativeQuery(sql, tClass).getResultList();
            em.flush();

            txn.commit();
        } catch (RuntimeException e) {
            if (txn.isActive()) txn.rollback();
            throw e;
        } finally {
            if (em.isOpen()) em.close();
        }
        em.close();

        return resultList;
    }
}
