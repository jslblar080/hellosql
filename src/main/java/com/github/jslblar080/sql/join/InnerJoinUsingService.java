package com.github.jslblar080.sql.join;

import com.github.jslblar080.BaseEntity;
import com.github.jslblar080.BaseRepository;
import com.github.jslblar080.QueryService;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.*;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.List;

public class InnerJoinUsingService extends QueryService {

    public InnerJoinUsingService(BaseRepository repository) {
        super(repository);
    }

    @Entity
    @Table(name = "blog")
    @RequiredArgsConstructor
    private static class Blog extends BaseEntity {

        @Id
        @GeneratedValue
        @Column(name = "blog_id")
        private Long id;

        @NonNull
        private String title;
    }

    @Entity
    @Table(name = "blog_comment")
    @RequiredArgsConstructor
    private static class BlogComment extends BaseEntity {

        @Id
        @GeneratedValue
        private Long id;

        @NonNull
        @ManyToOne
        @JoinColumn(name = "blog_id")
        private Blog blog;

        @NonNull
        private String review;
    }

    @PostConstruct
    private void initDB() {

        Blog blog1 = new Blog("Java");
        Blog blog2 = new Blog("Hibernate");
        Blog blog3 = new Blog("JPA");

        repository.save(blog1);
        repository.save(blog2);
        repository.save(blog3);

        repository.save(new BlogComment(blog1, "Good"));
        repository.save(new BlogComment(blog2, "Excellent"));
        repository.save(new BlogComment(blog3, "Awesome"));
    }

    public List<Tuple> extractBlogs() {

        String sql = """
                SELECT
                    b.blog_id id,
                    b.title title,
                    bc.review review
                FROM
                    blog b
                INNER JOIN
                    blog_comment bc USING (blog_id)
                ORDER BY
                    bc.blog_id
                """;

        return repository.getResultList(sql, Tuple.class);
    }
}
