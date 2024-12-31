package com.github.jslblar080.config;

import com.github.jslblar080.BaseRepository;
import com.github.jslblar080.repository.JpaRepository;
import com.github.jslblar080.QueryService;
import com.github.jslblar080.sql.join.CrossJoinService;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(DataConfig.class)
public class AppConfig {

    @Autowired
    private EntityManagerFactory emf;

    @Bean
    public QueryService crossJoinService() {

        return new CrossJoinService(jpaRepository());
    }

    private BaseRepository jpaRepository() {

        return new JpaRepository(emf);
    }
}
