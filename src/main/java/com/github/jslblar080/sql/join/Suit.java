package com.github.jslblar080.sql.join;

import com.github.jslblar080.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "suits")
@RequiredArgsConstructor
@Getter
public class Suit extends BaseEntity {

    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    @Setter
    private String name;

    @NonNull
    @Setter
    private String symbol;
}
