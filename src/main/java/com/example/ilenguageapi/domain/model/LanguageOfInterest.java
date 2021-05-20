package com.example.ilenguageapi.domain.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "language_of_interests")
public class LanguageOfInterest extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            mappedBy = "languageOfInterests")
    private List<User> users;

    public LanguageOfInterest() {
    }

    public LanguageOfInterest(Long id, String nameLanguage) {
        this.id = id;
        this.name = nameLanguage;
    }

    public List<User> getUsers() {
        return users;
    }

    public LanguageOfInterest setUsers(List<User> users) {
        this.users = users;
        return this;
    }
    public Long getId() {
        return id;
    }

    public LanguageOfInterest setId(Long id) {
        this.id = id;
        return this;
    }

    public String getNameLanguage() {
        return name;
    }

    public LanguageOfInterest setNameLanguage(String nameLanguage) {
        this.name = nameLanguage;
        return this;
    }
}
