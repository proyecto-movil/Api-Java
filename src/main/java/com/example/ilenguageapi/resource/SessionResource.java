package com.example.ilenguageapi.resource;

import com.example.ilenguageapi.domain.model.AuditModel;

public class SessionResource extends AuditModel {
    private long id;
    private String startAt;
    private String endAt;
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

    public String getStartAt() {
        return startAt;
    }

    public SessionResource setStartAt(String startAt) {
        this.startAt = startAt;
        return this;
    }

    public String getEndAt() {
        return endAt;
    }

    public SessionResource setEndAt(String endAt) {
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
