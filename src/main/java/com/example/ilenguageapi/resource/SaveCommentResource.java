package com.example.ilenguageapi.resource;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SaveCommentResource {
    @NotNull
    @NotBlank
    @Size(max = 255)
    private String content;
    @NotNull
    private double rating;

    public String getContent() {
        return content;
    }

    public SaveCommentResource setContent(String content) {
        this.content = content;
        return this;
    }

    public double getRating() {
        return rating;
    }

    public SaveCommentResource setRating(double rating) {
        this.rating = rating;
        return this;
    }
}
