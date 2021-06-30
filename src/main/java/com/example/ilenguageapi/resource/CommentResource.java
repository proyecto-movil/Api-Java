package com.example.ilenguageapi.resource;

import com.example.ilenguageapi.domain.model.AuditModel;

public class CommentResource extends AuditModel {
    private Long id;
    private String content;
    private double rating;

    public Long getId() {
        return id;
    }

    public CommentResource setId(Long id) {
        this.id = id;
        return this;
    }

    public String getContent() {
        return content;
    }

    public CommentResource setContent(String content) {
        this.content = content;
        return this;
    }

    public double getRating() {
        return rating;
    }

    public CommentResource setRating(double rating) {
        this.rating = rating;
        return this;
    }
}
