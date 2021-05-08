package com.example.ilenguageapi.resource;

public class ScheduleResource {
    private int id;
    private String name;
    private int hoursDuration;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getHoursDuration() {
        return hoursDuration;
    }


    public ScheduleResource setId(int id) {
        this.id = id;
        return this;
    }

    public ScheduleResource setName(String name) {
        this.name = name;
        return this;
    }

    public ScheduleResource setHoursDuration(int hoursDuration) {
        this.hoursDuration = hoursDuration;
        return this;
    }
}
