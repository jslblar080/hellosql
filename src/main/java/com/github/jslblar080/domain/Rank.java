package com.github.jslblar080.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ranks")
@RequiredArgsConstructor
@Getter
public class Rank {

    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    @Setter
    private String name;

    @NonNull
    @Column(name = "rank_value")
    @Setter
    private Integer rankValue;
}
