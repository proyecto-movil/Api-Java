package com.example.ilenguageapi.domain.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "schedules")
public class Schedule extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    private  String name;
    @NotNull
    public int hoursDuration;
    @NotNull
    private String descriptionSchedule;



    public Schedule(){

    }
    public Schedule( int id,int hoursDuration,String name, String descriptionSchedule) {
        this.id = id;
        this.hoursDuration =hoursDuration;
        this.name = name;
        this.descriptionSchedule = descriptionSchedule;

    }



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
