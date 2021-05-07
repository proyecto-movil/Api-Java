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

    public void setId(long id) {
        this.id = id;
    }

    public String getStartAt() {
        return startAt;
    }

    public void setStartAt(String startAt) {
        this.startAt = startAt;
    }

    public String getEndAt() {
        return endAt;
    }

    public void setEndAt(String endAt) {
        this.endAt = endAt;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
