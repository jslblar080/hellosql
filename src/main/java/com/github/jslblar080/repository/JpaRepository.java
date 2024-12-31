package com.github.jslblar080.repository;

import com.github.jslblar080.domain.BaseEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;

public class JpaRepository implements BaseRepository {

    private final EntityManagerFactory emf;

    private EntityManager em;

    public JpaRepository(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public void save(BaseEntity entity) {

        em = emf.createEntityManager();
        em.getTransaction().begin();

        em.persist(entity);

        em.getTransaction().commit();
        em.close();
    }

    @Override
    public <T> List<T> getResultList(String sql, Class<T> tClass) {

        em = emf.createEntityManager();
        em.getTransaction().begin();

        List<T> resultList = em.createNativeQuery(sql, tClass).getResultList();

        em.getTransaction().commit();
        em.close();

        return resultList;
    }
}
