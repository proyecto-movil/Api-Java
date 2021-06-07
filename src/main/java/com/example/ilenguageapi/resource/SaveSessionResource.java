package com.example.ilenguageapi.resource;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class SaveSessionResource {

    @NotNull
    private LocalDate startAt;

    @NotNull
    private LocalDate endAt;

    @NotNull
    @NotBlank
    @Size(max = 250)
    private String link;

    @NotNull
    @NotBlank
    @Size(max = 50)
    private String state;

    @NotNull
    @NotBlank
    @Size(max = 100)
    private String topic;

    @NotNull
    @NotBlank
    @Size(max = 250)
    private String information;

    public LocalDate getStartAt() {
        return startAt;
    }

    public SaveSessionResource setStartAt(LocalDate startAt) {
        this.startAt = startAt;
        return this;
    }

    public LocalDate getEndAt() {
        return endAt;
    }

    public SaveSessionResource setEndAt(LocalDate endAt) {
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

    public String getState() {
        return state;
    }

    public SaveSessionResource setState(String state) {
        this.state = state;
        return this;
    }

    public String getTopic() {
        return topic;
    }

    public SaveSessionResource setTopic(String topic) {
        this.topic = topic;
        return this;
    }

    public String getInformation() {
        return information;
    }

    public SaveSessionResource setInformation(String information) {
        this.information = information;
        return this;
    }
}
