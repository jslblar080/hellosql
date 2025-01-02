package com.github.jslblar080.sql.join;

import com.github.jslblar080.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "localization")
@RequiredArgsConstructor
@Getter
public class Localization extends BaseEntity {

    @Id
    @GeneratedValue
    private Long local_id;

    @NonNull
    @Setter
    private String country;

    @NonNull
    @Setter
    private String language;
}
