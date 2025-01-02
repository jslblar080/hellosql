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
class NaturalJoinServiceTest {

    @Autowired
    private NaturalJoinService service;

    @Test
    void extractPosts() {

        List<Tuple> posts = service.extractPosts();
        System.out.println(posts);

        Tuple post1 = posts.get(0);
        Tuple post2 = posts.get(1);
        Tuple post3 = posts.get(2);

        assertThat(1L).isEqualTo(post1.get("id"));
        assertThat(2L).isEqualTo(post2.get("id"));
        assertThat(3L).isEqualTo(post3.get("id"));

        assertThat("8 Java Stream Tips").isEqualTo(post1.get("title"));
        assertThat("10 Astuces Pour Hibernate").isEqualTo(post2.get("title"));
        assertThat("3 Jahre Bloggen").isEqualTo(post3.get("title"));

        assertThat("English").isEqualTo(post1.get("language"));
        assertThat("French").isEqualTo(post2.get("language"));
        assertThat("German").isEqualTo(post3.get("language"));
    }
}
