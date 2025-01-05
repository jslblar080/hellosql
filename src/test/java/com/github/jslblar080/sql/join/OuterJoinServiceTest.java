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
class OuterJoinServiceTest {

    @Autowired
    private OuterJoinService service;

    @Test
    void extractPostsLeftRightJoin() {

        List<Tuple> posts = service.extractPostsLeftJoin();
        System.out.println(posts);

        assertThat(posts.toString()).isEqualTo(service.extractPostsRightJoin().toString());

        Tuple post1 = posts.get(0);
        Tuple post2 = posts.get(1);
        Tuple post3 = posts.get(2);
        Tuple post4 = posts.get(3);

        assertThat(1L).isEqualTo(post1.get("id"));
        assertThat(1L).isEqualTo(post2.get("id"));
        assertThat(2L).isEqualTo(post3.get("id"));
        assertThat(3L).isEqualTo(post4.get("id"));

        assertThat("Java").isEqualTo(post1.get("title"));
        assertThat("Java").isEqualTo(post2.get("title"));
        assertThat("Hibernate").isEqualTo(post3.get("title"));
        assertThat("JPA").isEqualTo(post4.get("title"));

        assertThat("Good").isEqualTo(post1.get("review"));
        assertThat("Excellent").isEqualTo(post2.get("review"));
        assertThat("Awesome").isEqualTo(post3.get("review"));
        assertThat(post4.get("review")).isNull();
    }

    @Test
    void extractPostsFullJoin() {

        List<Tuple> posts = service.extractPostsFullJoinEquivalent();
        System.out.println(posts);

        Tuple post1 = posts.get(0);
        Tuple post2 = posts.get(1);
        Tuple post3 = posts.get(2);
        Tuple post4 = posts.get(3);

        assertThat(post1.get("id")).isNull();
        assertThat(7L).isEqualTo(post2.get("id"));
        assertThat(8L).isEqualTo(post3.get("id"));
        assertThat(9L).isEqualTo(post4.get("id"));

        assertThat(post1.get("title")).isNull();
        assertThat("8 Java Stream Tips").isEqualTo(post2.get("title"));
        assertThat("10 Hibernate Tips").isEqualTo(post3.get("title"));
        assertThat("3 years of blogging").isEqualTo(post4.get("title"));

        assertThat("JPA").isEqualTo(post1.get("name"));
        assertThat("Java").isEqualTo(post2.get("name"));
        assertThat("Hibernate").isEqualTo(post3.get("name"));
        assertThat(post4.get("name")).isNull();
    }
}
