package com.example.ilenguageapi.resource;

import com.example.ilenguageapi.domain.model.AuditModel;

public class LanguageOfInterestResource extends AuditModel {
    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public LanguageOfInterestResource setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public LanguageOfInterestResource setName(String name) {
        this.name = name;
        return this;
    }
}
