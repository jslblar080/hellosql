package com.github.jslblar080.repository;

import java.util.List;

public interface BaseRepository {

    void save(Object entity);

    <T> List<T> getResultList(String sql, Class<T> tClass);
}
