package com.github.jslblar080.repository;

import com.github.jslblar080.domain.BaseEntity;

import java.util.List;

public interface BaseRepository {

    void save(BaseEntity entity);

    <T> List<T> getResultList(String sql, Class<T> tClass);
}
