package com.example.ilenguageapi.domain.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name = "user_schedules")
public class UserSchedule {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Long userScheduleId;





    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "schedule_id", nullable = false, insertable = false, updatable = false)
    private Schedule schedule;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name ="user_id", nullable = false , insertable = false, updatable = false)
    private User user;

    public UserSchedule(Long userScheduleId,  Schedule schedule, User user) {
        this.userScheduleId = userScheduleId;

        this.schedule = schedule;
        this.user = user;
    }

    public UserSchedule() {
    }

    public Long getUserScheduleId() {
        return userScheduleId;
    }





    public Schedule getSchedule() {
        return schedule;
    }

    public User getUser() {
        return user;
    }

    public void setUserScheduleId(Long userScheduleId) {
        this.userScheduleId = userScheduleId;
    }



    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
