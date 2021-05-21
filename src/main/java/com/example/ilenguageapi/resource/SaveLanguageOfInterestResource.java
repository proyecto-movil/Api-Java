package com.example.ilenguageapi.resource;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SaveLanguageOfInterestResource {
    @NotNull
    @NotBlank
    @Size(max = 20)
    private String name;

    public String getName() {
        return name;
    }

    public SaveLanguageOfInterestResource setName(String name) {
        this.name = name;
        return this;
    }
}
