package com.example.ilenguageapi.resource;

public class ScheduleResource {
    private Long id;

    private String day;

    public long getId() {
        return id;
    }


    public String getDay() {
        return day;
    }



    public ScheduleResource setId(Long id) {
        this.id = id;
        return this;
    }

    public ScheduleResource setDay(String day) {
        this.day = day;
        return this;
    }
}
