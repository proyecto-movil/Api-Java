package com.example.ilenguageapi.domain.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "schedules")
public class Schedule extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private  String day;

    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY, mappedBy ="schedule")
    private List<UserSchedule> users;

    public Schedule(){

    }
    public Schedule( Long id,String day) {
        this.id = id;
        this.day = day;

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {

        this.id = id;
    }

    public List<UserSchedule> getUsers() {
        return users;
    }

    public void setUsers(List<UserSchedule> users) {
        this.users = users;
    }


    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }



}
