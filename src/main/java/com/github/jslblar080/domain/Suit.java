package com.github.jslblar080.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "suits")
@RequiredArgsConstructor
@Getter
public class Suit {

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
