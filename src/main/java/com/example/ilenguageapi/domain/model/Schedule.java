package com.example.ilenguageapi.domain.model;

import java.util.List;

public class Schedule {
    private int id;
    public String NameCourse;
    public int HoursDuration;
    public Schedule( int id,int hoursDuration,String nameCourse) {
        this.id = id;
        HoursDuration=hoursDuration;
        NameCourse = nameCourse;
    }

    private String descriptionSchedule;

    private Day day;

    public String getDescriptionSchedule() {
        return descriptionSchedule;
    }

    public void setDescriptionSchedule(String descriptionSchedule) {
        this.descriptionSchedule = descriptionSchedule;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public Day day() {
        return day;
    }

    public void setDay(Day day) {
        this.day = day;
    }

    public int getHoursDuration() {
        return  HoursDuration;
    }

    public void setHoursDuration(int hoursDuration) {
        HoursDuration = hoursDuration;
    }

    public String getNameCourse() {
        return NameCourse;
    }

    public void setNameCourse(String nameCourse) {
        NameCourse = nameCourse;
    }
}
