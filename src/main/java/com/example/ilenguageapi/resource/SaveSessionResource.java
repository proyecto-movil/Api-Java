package com.example.ilenguageapi.resource;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SaveSessionResource {

    @NotNull
    @NotBlank
    @Size(max = 50)
    private String startAt;
    @NotNull
    @NotBlank
    @Size(max = 50)
    private String endAt;
    @NotNull
    @NotBlank
    @Size(max = 100)
    private String link;

    public String getStartAt() {
        return startAt;
    }

    public SaveSessionResource setStartAt(String startAt) {
        this.startAt = startAt;
        return this;
    }

    public String getEndAt() {
        return endAt;
    }

    public SaveSessionResource setEndAt(String endAt) {
        this.endAt = endAt;
        return this;
    }

    public String getLink() {
        return link;
    }

    public SaveSessionResource setLink(String link) {
        this.link = link;
        return this;
    }
}
