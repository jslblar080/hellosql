package com.github.jslblar080.sql.join;

import com.github.jslblar080.config.AppConfig;
import jakarta.persistence.Tuple;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AppConfig.class)
class InnerJoinUsingServiceTest {

    @Autowired
    private InnerJoinUsingService service;

    @Test
    void extractMetas() {

        List<Tuple> blogs = service.extractBlogs();
        System.out.println(blogs);

        Tuple blog1 = blogs.get(0);
        Tuple blog2 = blogs.get(1);
        Tuple blog3 = blogs.get(2);

        assertThat(1L).isEqualTo(blog1.get("id"));
        assertThat(2L).isEqualTo(blog2.get("id"));
        assertThat(3L).isEqualTo(blog3.get("id"));

        assertThat("Java").isEqualTo(blog1.get("title"));
        assertThat("Hibernate").isEqualTo(blog2.get("title"));
        assertThat("JPA").isEqualTo(blog3.get("title"));

        assertThat("Good").isEqualTo(blog1.get("review"));
        assertThat("Excellent").isEqualTo(blog2.get("review"));
        assertThat("Awesome").isEqualTo(blog3.get("review"));
    }
}
