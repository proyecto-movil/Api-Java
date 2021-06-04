package com.example.ilenguageapi.domain.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "schedules")
public class Schedule extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    private  String day;






    public Schedule(){

    }
    public Schedule( long id,String day) {
        this.id = id;


        this.day = day;

    }


    public long getId() {
        return id;
    }

    public void setId(long id) {

        this.id = id;
    }


    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }




}
