package com.example.ilenguageapi.domain.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="sessions")
public class Session extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    private String startAt;

    @NotNull
    private String endAt;

    @NotNull
    private String link;

    @NotNull
    private String state;

    @NotNull
    private String topic;

    @NotNull
    private String information;

    public Session(@NotNull String startAt, @NotNull String endAt, @NotNull String link, @NotNull String state, @NotNull String topic, @NotNull String information) {
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

    public String getStartAt() {
        return startAt;
    }

    public String getEndAt() {
        return endAt;
    }

    public String getLink() {
        return link;
    }

    public String getState() { return state; }

    public String getTopic() { return topic; }

    public String getInformation() { return information; }

    public Session setId(long id) {
        this.id = id;
        return this;
    }

    public Session setStartAt(String startAt) {
        this.startAt = startAt;
        return this;
    }

    public Session setEndAt(String endAt) {
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
