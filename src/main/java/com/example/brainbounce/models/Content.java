package com.example.brainbounce.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "contents")
public class Content {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_content")
    private Long id;

    @Column(name = "content")
    private String content;

    @Column(name = "community_id")
    private Long communityId;

    @Column(name = "type_of_content_id")
    private Integer typeOfContentId;

    @Column(name = "num_of_likes")
    private Integer numOfLikes;

    @Column(name = "is_community_content")
    private Boolean isCommunityContent;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "name")
    private String name;

    // Assuming created_by references a User entity
    @Column(name = "created_by")
    private Long createdBy;
}
