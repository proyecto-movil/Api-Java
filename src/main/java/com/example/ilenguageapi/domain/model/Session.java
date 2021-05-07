package com.example.ilenguageapi.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

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

    /*
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private User user;
    */

    public Session(String startAt, String endAt, String link) {
        this.startAt = startAt;
        this.endAt = endAt;
        this.link = link;
    }

    public Session() {

    }

    public void setId(long id) {
        this.id = id;
    }

    public void setStartAt(String startAt) {
        this.startAt = startAt;
    }

    public void setEndAt(String endAt) {
        this.endAt = endAt;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
