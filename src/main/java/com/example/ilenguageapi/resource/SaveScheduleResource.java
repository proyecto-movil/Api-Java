package com.example.ilenguageapi.resource;


import javax.persistence.Lob;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.constraints.Positive;
public class SaveScheduleResource {

    @NotBlank
    @Size(min = 3, max = 20, message="NameCourse must have between 3 and 20 characters")
    private String nameCourse;

    @NotNull
    @Positive(message ="Hours must have a positive value")
    private String hoursDuration;


}
