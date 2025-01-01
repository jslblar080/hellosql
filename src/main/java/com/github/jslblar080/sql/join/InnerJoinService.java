package com.github.jslblar080.sql.join;

import com.github.jslblar080.BaseRepository;
import com.github.jslblar080.QueryService;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.Tuple;

import java.util.List;

public class InnerJoinService extends QueryService {

    public InnerJoinService(BaseRepository repository) {
        super(repository);
    }

    @PostConstruct
    private void initDB() {

        Post post1 = new Post("Java");
        Post post2 = new Post("Hibernate");
        Post post3 = new Post("JPA");

        repository.save(post1);
        repository.save(post2);
        repository.save(post3);

        repository.save(new PostComment(post1, "Good"));
        repository.save(new PostComment(post2, "Excellent"));
        repository.save(new PostComment(post3, "Awesome"));
    }

    public List<Tuple> extractPosts() {

        String sql = """
                SELECT
                    p.id id,
                    p.title title,
                    pc.review review
                FROM
                    post p
                INNER JOIN
                    post_comment pc ON pc.post_id = p.id
                ORDER BY
                    pc.id
                """;

        return repository.getResultList(sql, Tuple.class);
    }

    public List<Tuple> extractPostsWithoutJoin() {

        String sqlThetaStyle = """
                SELECT
                    p.id id,
                    p.title title,
                    pc.review review
                FROM
                    post p,
                    post_comment pc
                WHERE
                    pc.post_id = p.id
                ORDER BY
                    pc.id
                """;

        return repository.getResultList(sqlThetaStyle, Tuple.class);
    }
}
