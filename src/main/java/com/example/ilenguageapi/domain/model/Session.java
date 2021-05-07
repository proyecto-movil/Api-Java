package com.example.ilenguageapi.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="sessions")
public class Session extends AuditModel {

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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    private String startAt;

    @NotNull
    private String endAt;

    @NotNull
    private String link;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private User user;


    public SessionDetail getSessionDetail() {
        return sessionDetail;
    }

    public void setSessionDetail(SessionDetail sessionDetail) {
        this.sessionDetail = sessionDetail;
    }

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "session_Detail_id", nullable = false)
    @JsonIgnore
    private SessionDetail sessionDetail;

    public Session(@NotNull String startAt, @NotNull String endAt, @NotNull String link) {
        this.startAt = startAt;
        this.endAt = endAt;
        this.link = link;
    }

    public Session() {

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
