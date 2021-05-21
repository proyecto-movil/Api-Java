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

    public Session(@NotNull String startAt, @NotNull String endAt, @NotNull String link) {
        this.startAt = startAt;
        this.endAt = endAt;
        this.link = link;
    }

    public Session() {

    }

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
}
