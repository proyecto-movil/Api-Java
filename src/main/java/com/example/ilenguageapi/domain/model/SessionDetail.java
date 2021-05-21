package com.example.ilenguageapi.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "session_details")
public class SessionDetail extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    private String state;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "session_id", nullable = false)
    @JsonIgnore
    private Session session;

    public SessionDetail(@NotNull String state) {
        this.state = state;
    }

    public SessionDetail() {

    }

    public long getId() {
        return id;
    }

    public SessionDetail setId(long id) {
        this.id = id;
        return this;
    }

    public String getState() {
        return state;
    }

    public SessionDetail setState(String state) {
        this.state = state;
        return this;
    }

    public Session getSession() {
        return session;
    }

    public SessionDetail setSession(Session session) {
        this.session = session;
        return this;
    }
}
