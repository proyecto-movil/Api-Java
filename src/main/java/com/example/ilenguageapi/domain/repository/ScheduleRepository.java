package com.example.ilenguageapi.domain.repository;


import com.example.ilenguageapi.domain.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {


    public Optional<Schedule> findByDay(String day);

}
