package com.example.ilenguageapi.domain.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

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

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "user_schedule",
            joinColumns = {@JoinColumn(name="schedule_id")},
            inverseJoinColumns = {@JoinColumn(name="user_id")})
    private List<User> users;

    public Schedule(){

    }
    public Schedule( int id,int hoursDuration,String name, String descriptionSchedule, List<User> users) {
        this.id = id;
        this.hoursDuration =hoursDuration;
        this.name = name;
        this.descriptionSchedule = descriptionSchedule;
        this.users = users;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
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
