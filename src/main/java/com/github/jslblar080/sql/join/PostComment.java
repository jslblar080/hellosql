package com.github.jslblar080.sql.join;

import com.github.jslblar080.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "post_comment")
@RequiredArgsConstructor
@Getter
public class PostComment extends BaseEntity {

    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    @ManyToOne
    @Setter
    private Post post;

    @NonNull
    @Setter
    private String review;
}
