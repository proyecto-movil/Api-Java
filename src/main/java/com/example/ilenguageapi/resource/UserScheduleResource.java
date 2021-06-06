package com.example.ilenguageapi.resource;

import com.example.ilenguageapi.domain.model.Schedule;
import com.example.ilenguageapi.domain.model.User;


public class UserScheduleResource {
    private Long userScheduleId;


    private User user;
    private Schedule schedule;

    public Long getUserScheduleId() {
        return userScheduleId;
    }

    public void setUserScheduleId(Long userScheduleId) {
        this.userScheduleId = userScheduleId;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSubscription(Schedule schedule) {
        this.schedule = schedule;
    }
}
