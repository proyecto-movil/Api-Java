package com.example.ilenguageapi.domain.model;

import java.util.List;

public class Schedule {

    public String NameCourse;
    public int HoursDuration;
    public Schedule( int hoursDuration,String nameCourse) {

        HoursDuration=hoursDuration;
        NameCourse = nameCourse;
    }

    private String descriptionSchedule;
s
    private Day day;

    public String getDescriptionSchedule() {
        return descriptionSchedule;
    }

    public void setDescriptionSchedule(String descriptionSchedule) {
        this.descriptionSchedule = descriptionSchedule;
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
