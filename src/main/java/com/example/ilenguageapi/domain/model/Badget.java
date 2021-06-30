package com.example.ilenguageapi.domain.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "badgets")
public class Badget extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String title;
    @NotNull
    private String description;
    @NotNull
    private String imgSrc;
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            mappedBy = "badgets")
    private List<User> users;

    public Badget() {
    }

    public Badget(@NotNull String title,@NotNull String description,@NotNull String imgSrc) {
        this.title = title;
        this.description = description;
        this.imgSrc = imgSrc;
    }

    public Long getId() {
        return id;
    }

    public Badget setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Badget setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Badget setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getImgSrc() {
        return imgSrc;
    }

    public Badget setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
        return this;
    }

    public List<User> getUsers() {
        return users;
    }

    public Badget setUsers(List<User> users) {
        this.users = users;
        return this;
    }
}
