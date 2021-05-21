package com.example.ilenguageapi.domain.repository;


import com.example.ilenguageapi.domain.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {

    public Optional<Schedule> findByName(String name);
    public Optional<Schedule> findByHoursDuration(int hoursDuration);
    public Optional<Schedule> findByDay(String day);

}
