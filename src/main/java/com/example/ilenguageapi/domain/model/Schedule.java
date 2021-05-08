package com.example.ilenguageapi.domain.model;

import java.util.List;

public class Schedule {
    private  String Name;
    private int id;
    public int HoursDuration;
    public Schedule(){

    }
    public Schedule( int id,int hoursDuration,String name) {
        this.id = id;
        HoursDuration=hoursDuration;
        Name = name;
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

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }


}
