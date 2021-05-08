package com.example.ilenguageapi.resource;

import com.example.ilenguageapi.domain.model.AuditModel;

public class SessionResource extends AuditModel {
    private long id;
    private String startAt;
    private String endAt;
    private String link;

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
