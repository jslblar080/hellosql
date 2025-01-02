package com.github.jslblar080.sql.join;

import com.github.jslblar080.BaseRepository;
import com.github.jslblar080.QueryService;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.Tuple;

import java.util.List;

public class NaturalJoinService extends QueryService {

    public NaturalJoinService(BaseRepository repository) {
        super(repository);
    }

    @PostConstruct
    private void initDB() {

        Localization enUs = new Localization("USA", "English");
        Localization frFr = new Localization("France", "French");
        Localization deDe = new Localization("Germany", "German");

        repository.save(enUs);
        repository.save(frFr);
        repository.save(deDe);

        repository.save(Post.createPost("8 Java Stream Tips", enUs));
        repository.save(Post.createPost("10 Astuces Pour Hibernate", frFr));
        repository.save(Post.createPost("3 Jahre Bloggen", deDe));
    }

    public List<Tuple> extractPosts() {

        String sql = """
                SELECT
                    p.local_id id,
                    p.title title,
                    l.language language
                FROM
                    post p
                NATURAL JOIN
                    localization l
                ORDER BY
                    l.local_id
                """;

        return repository.getResultList(sql, Tuple.class);
    }
}
