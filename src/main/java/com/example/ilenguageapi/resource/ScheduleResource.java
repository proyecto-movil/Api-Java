package com.example.ilenguageapi.resource;

public class ScheduleResource {
    private long id;

    private String day;

    public long getId() {
        return id;
    }


    public String getDay() {
        return day;
    }



    public ScheduleResource setId(long id) {
        this.id = id;
        return this;
    }

    public ScheduleResource setDay(String day) {
        this.day = day;
        return this;
    }
}
