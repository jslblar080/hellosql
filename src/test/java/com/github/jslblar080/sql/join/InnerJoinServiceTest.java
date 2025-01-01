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
class InnerJoinServiceTest {

    @Autowired
    private InnerJoinService service;

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

        assertThat("Java").isEqualTo(post1.get("title"));
        assertThat("Hibernate").isEqualTo(post2.get("title"));
        assertThat("JPA").isEqualTo(post3.get("title"));

        assertThat("Good").isEqualTo(post1.get("review"));
        assertThat("Excellent").isEqualTo(post2.get("review"));
        assertThat("Awesome").isEqualTo(post3.get("review"));
    }

    @Test
    void comparePosts() {

        List<Tuple> postsWithJoin = service.extractPosts();
        List<Tuple> postsWithoutJoin = service.extractPostsWithoutJoin();

        assertThat(postsWithJoin.toString()).isEqualTo(postsWithoutJoin.toString());
    }
}
