package com.github.jslblar080;

import java.util.List;

public interface BaseRepository {

    void save(BaseEntity entity);

    <T> List<T> getResultList(String sql, Class<T> tClass);
}
