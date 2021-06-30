package com.example.ilenguageapi.resource;

import com.example.ilenguageapi.domain.model.AuditModel;

public class BadgetResource extends AuditModel {
    private Long id;
    private String title;
    private String description;
    private String imgSrc;

    public Long getId() {
        return id;
    }

    public BadgetResource setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public BadgetResource setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public BadgetResource setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getImgSrc() {
        return imgSrc;
    }

    public BadgetResource setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
        return this;
    }
}
