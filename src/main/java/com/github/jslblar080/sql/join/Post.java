package com.github.jslblar080.sql.join;

import com.github.jslblar080.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "post")
@RequiredArgsConstructor
@Getter
public class Post extends BaseEntity {

    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    @Setter
    private String title;

    @ManyToOne
    @JoinColumn(name = "local_id")
    private Localization localization;

    public static Post createPost(String title, Localization localization) {

        Post post = new Post(title);
        post.localization = localization;
        return post;
    }
}
