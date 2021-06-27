package com.example.ilenguageapi.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="sessions")
public class Session extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    private LocalDate startAt;

    @NotNull
    private LocalDate endAt;

    @NotNull
    private String link;

    @NotNull
    private String state;

    @NotNull
    private String topic;

    @NotNull
    private String information;


    public Session(@NotNull LocalDate startAt, @NotNull LocalDate endAt, @NotNull String link, @NotNull String state, @NotNull String topic, @NotNull String information) {
        this.startAt = startAt;
        this.endAt = endAt;
        this.link = link;
        this.state = state;
        this.topic = topic;
        this.information = information;
    }

    public Session() { }

    public long getId() {
        return id;
    }

    public LocalDate getStartAt() {
        return startAt;
    }

    public LocalDate getEndAt() {
        return endAt;
    }

    public String getLink() {
        return link;
    }

    public String getState() { return state; }

    public String getTopic() { return topic; }

    public String getInformation() { return information; }


    public List<User> getUsers() {
        return users;
    }

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name ="user_sessions",
            joinColumns =  {@JoinColumn(name = "session_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")})
    private List<User> users;


    public boolean isRelatedWith(User user ) {
        return this.getUsers().contains(user);
    }

    public Session userWith(User user) {
        if(!isRelatedWith(user)) {
            this.getUsers().add(user);
        }
        return this;
    }

    public Session unUserWith(User user) {
        if(this.isRelatedWith(user))
            this.getUsers().remove(user);
        return this;
    }

    public Session setId(long id) {
        this.id = id;
        return this;
    }

    public Session setStartAt(LocalDate startAt) {
        this.startAt = startAt;
        return this;
    }

    public Session setEndAt(LocalDate endAt) {
        this.endAt = endAt;
        return this;
    }

    public Session setLink(String link) {
        this.link = link;
        return this;
    }

    public Session setState(String state) {
        this.state = state;
        return this;
    }

    public Session setTopic(String topic) {
        this.topic = topic;
        return this;
    }

    public Session setInformation(String information) {
        this.information = information;
        return this;
    }


}
