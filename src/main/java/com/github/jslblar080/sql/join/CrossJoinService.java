package com.github.jslblar080.sql.join;

import com.github.jslblar080.BaseRepository;
import com.github.jslblar080.QueryService;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.Tuple;

import java.util.List;

public class CrossJoinService extends QueryService {

    public CrossJoinService(BaseRepository repository) {
        super(repository);
    }

    @PostConstruct
    private void initDB() {

        repository.save(new Rank("King", 13));
        repository.save(new Rank("Queen", 12));
        repository.save(new Rank("Jack", 11));

        repository.save(new Suit("Club", "♣"));
        repository.save(new Suit("Diamond", "♦"));
        repository.save(new Suit("Heart", "♥"));
        repository.save(new Suit("Spade", "♠"));
    }

    public List<Tuple> extractCards() {

        String sql = """
                SELECT
                    r.name rank,
                    s.symbol suit
                FROM
                    ranks r
                CROSS JOIN
                    suits s
                ORDER BY
                    r.rank_value DESC,
                    s.name ASC
                """;

        return repository.getResultList(sql, Tuple.class);
    }

    public List<Tuple> extractCardsWithoutJoin() {

        String sqlThetaStyle = """
                SELECT
                    r.name rank,
                    s.symbol suit
                FROM
                    ranks r,
                    suits s
                ORDER BY
                    r.rank_value DESC,
                    s.name ASC
                """;

        return repository.getResultList(sqlThetaStyle, Tuple.class);
    }
}
