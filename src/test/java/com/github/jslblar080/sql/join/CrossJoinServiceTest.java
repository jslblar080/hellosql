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
class CrossJoinServiceTest {

    @Autowired
    private CrossJoinService crossJoinService;

    @Test
    void extractCards() {

        String[][] arrayNameSymbol = {{"King", "Queen", "Jack"}, {"♣", "♦", "♥", "♠"}};

        List<Tuple> cards = crossJoinService.extractCards();
        System.out.println(cards);

        for (int i = 0, j = 0, k = 0; k < cards.size(); j++) {

            if (j == arrayNameSymbol[1].length) {
                i++;
                j = 0;
            }

            Tuple card = cards.get(k++);

            assertThat(arrayNameSymbol[0][i]).isEqualTo(card.get("rank"));
            assertThat(arrayNameSymbol[1][j]).isEqualTo(card.get("suit"));
        }
    }
}
