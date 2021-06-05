package com.example.ilenguageapi.resource;


import javax.persistence.Lob;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.constraints.Positive;
public class SaveScheduleResource {


    @NotBlank
    @Size(min = 3, max = 20, message="Day must have between 3 and 20 characters")
    private String day;

    public String getDay() {
        return day;
    }

    public SaveScheduleResource setDay(String day) {
        this.day = day;
        return this;
    }
}
