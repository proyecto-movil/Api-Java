package com.example.ilenguageapi.domain.model;

import org.w3c.dom.stylesheets.LinkStyle;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "topic_of_interest")
public class TopicOfInterest extends AuditModel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            mappedBy = "topicOfInterests")
    private List<User> users;

    public List<User> getUsers() {
        return users;
    }

    public TopicOfInterest setUsers(List<User> users) {
        this.users = users;
        return this;
    }


    public TopicOfInterest() {
    }

    public TopicOfInterest(@NotNull Long id, @NotNull String topicName) {
        this.id = id;
        this.name = topicName;
    }

    public Long getId() {
        return id;
    }

    public TopicOfInterest setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTopicName() {
        return name;
    }

    public TopicOfInterest setTopicName(String topicName) {
        this.name = topicName;
        return this;
    }
}
