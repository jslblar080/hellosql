package com.github.jslblar080.sql.join;

import com.github.jslblar080.BaseRepository;
import com.github.jslblar080.QueryService;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.Tuple;

import java.util.List;

public class OuterJoinService extends QueryService {

    public OuterJoinService(BaseRepository repository) {
        super(repository);
    }

    @PostConstruct
    private void initDB() {

        List<Tuple> posts = repository.getResultList("SELECT * FROM post", Tuple.class);
        List<Tuple> postComments = repository.getResultList("SELECT * FROM post_comment", Tuple.class);

        System.out.println(">>> posts for OuterJoinService: " + posts);
        System.out.println(">>> postComments for OuterJoinService: " + postComments);
    }

    public List<Tuple> extractPostsLeftJoin() {

        String sql = """
                SELECT
                    p.id id,
                    p.title title,
                    pc.review review
                FROM
                    post p
                LEFT JOIN
                    post_comment pc ON pc.post_id = p.id
                ORDER BY
                    p.id
                """;

        return repository.getResultList(sql, Tuple.class);
    }

    public List<Tuple> extractPostsRightJoin() {

        String sql = """
                SELECT
                    p.id id,
                    p.title title,
                    pc.review review
                FROM
                    post_comment pc
                RIGHT JOIN
                    post p ON pc.post_id = p.id
                ORDER BY
                    p.id
                """;

        return repository.getResultList(sql, Tuple.class);
    }
}
