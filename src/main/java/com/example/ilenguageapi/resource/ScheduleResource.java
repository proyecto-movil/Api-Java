package com.example.ilenguageapi.resource;

public class ScheduleResource {
    private int id;
    private String nameCourse;
    private int hoursDuration;

    public int getId() {
        return id;
    }

    public String getNameCourse() {
        return nameCourse;
    }

    public int getHoursDuration() {
        return hoursDuration;
    }

    public ScheduleResource setId(int id) {
        this.id = id;
        return this;
    }

    public ScheduleResource setNameCourse(String nameCourse) {
        this.nameCourse = nameCourse;
        return this;
    }

    public ScheduleResource setHoursDuration(int hoursDuration) {
        this.hoursDuration = hoursDuration;
        return this;
    }
}
