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

        Category categoryJava = new Category("Java");
        Category categoryHibernate = new Category("Hibernate");
        Category categoryJPA = new Category("JPA");

        repository.save(categoryJava);
        repository.save(categoryHibernate);
        repository.save(categoryJPA);

        repository.save(Post.createPost("8 Java Stream Tips", categoryJava));
        repository.save(Post.createPost("10 Hibernate Tips", categoryHibernate));
        repository.save(new Post("3 years of blogging"));
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
                WHERE
                    p.id >= 1 AND p.id <= 3
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
                WHERE
                    p.id >= 1 AND p.id <= 3
                ORDER BY
                    p.id
                """;

        return repository.getResultList(sql, Tuple.class);
    }

    public List<Tuple> extractPostsFullJoinEquivalent() {

        String sql = """
                SELECT
                    id,
                    title,
                    name
                FROM
                    (
                    SELECT
                        p.id id,
                        p.title title,
                        c.name name
                    FROM
                        post p
                    LEFT JOIN
                        category c ON c.id = p.category_id
                    UNION
                    SELECT
                        p.id id,
                        p.title title,
                        c.name name
                    FROM
                        post p
                    RIGHT JOIN
                        category c ON c.id = p.category_id
                    )
                WHERE
                    (id >= 7 AND id <= 9) OR id IS NULL
                ORDER BY
                    id
                """;

        return repository.getResultList(sql, Tuple.class);
    }
}
