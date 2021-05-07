package com.example.ilenguageapi.resource;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SaveSessionDetailResource {
    @NotNull
    @NotBlank
    @Size(max = 30)
    private String state;

    public String getState() {
        return state;
    }

    public SaveSessionDetailResource setState(String state) {
        this.state = state;
        return this;
    }
}
