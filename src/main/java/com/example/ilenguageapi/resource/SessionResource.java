package com.example.ilenguageapi.resource;

import com.example.ilenguageapi.domain.model.AuditModel;

import java.time.LocalDate;

public class SessionResource extends AuditModel {
    private long id;
    private LocalDate startAt;
    private LocalDate endAt;
    private String link;
    private String state;
    private String topic;
    private String information;

    public String getState() {
        return state;
    }

    public SessionResource setState(String state) {
        this.state = state;
        return this;
    }

    public String getTopic() {
        return topic;
    }

    public SessionResource setTopic(String topic) {
        this.topic = topic;
        return this;
    }

    public String getInformation() {
        return information;
    }

    public SessionResource setInformation(String information) {
        this.information = information;
        return this;
    }

    public long getId() {
        return id;
    }

    public SessionResource setId(long id) {
        this.id = id;
        return this;
    }

    public LocalDate getStartAt() {
        return startAt;
    }

    public SessionResource setStartAt(LocalDate startAt) {
        this.startAt = startAt;
        return this;
    }

    public LocalDate getEndAt() {
        return endAt;
    }

    public SessionResource setEndAt(LocalDate endAt) {
        this.endAt = endAt;
        return this;
    }

    public String getLink() {
        return link;
    }

    public SessionResource setLink(String link) {
        this.link = link;
        return this;
    }
}
