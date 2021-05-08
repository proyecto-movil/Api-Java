package com.example.ilenguageapi.domain.model;

public class Schedule {
    private  String name;
    private int id;
    public int hoursDuration;
    public Schedule(){

    }
    public Schedule( int id,int hoursDuration,String name) {
        this.id = id;
        this.hoursDuration =hoursDuration;
        this.name = name;
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
        return hoursDuration;
    }

    public void setHoursDuration(int hoursDuration) {
        this.hoursDuration = hoursDuration;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
