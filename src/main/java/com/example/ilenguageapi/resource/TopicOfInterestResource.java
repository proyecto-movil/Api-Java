package com.example.ilenguageapi.resource;

import com.example.ilenguageapi.domain.model.AuditModel;

public class TopicOfInterestResource extends AuditModel {
    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public TopicOfInterestResource setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public TopicOfInterestResource setName(String name) {
        this.name = name;
        return this;
    }
}
