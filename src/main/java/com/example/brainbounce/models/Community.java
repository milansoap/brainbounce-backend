package com.example.brainbounce.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "communities")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Community {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_community")
    private Long id;

    @Column(name = "invite_code")
    private String inviteCode;

    @Column(name = "description")
    private String description;

    @Column(name = "privacy_level")
    private Integer privacyLevel;

    @Column(name = "name")
    private String name;

    @Column(name = "members_count")
    private Integer membersCount;

}
