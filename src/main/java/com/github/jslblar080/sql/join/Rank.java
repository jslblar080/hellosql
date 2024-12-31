package com.github.jslblar080.sql.join;

import com.github.jslblar080.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ranks")
@RequiredArgsConstructor
@Getter
public class Rank extends BaseEntity {

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
