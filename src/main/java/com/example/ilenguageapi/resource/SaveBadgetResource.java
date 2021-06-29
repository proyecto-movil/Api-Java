package com.example.ilenguageapi.resource;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class SaveBadgetResource {

    @NotNull
    @NotBlank
    private String title;
    @NotNull
    @NotBlank
    private String description;
    @NotNull
    @NotBlank
    private String imgSrc;

    public String getTitle() {
        return title;
    }

    public SaveBadgetResource setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public SaveBadgetResource setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getImgSrc() {
        return imgSrc;
    }

    public SaveBadgetResource setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
        return this;
    }
}
