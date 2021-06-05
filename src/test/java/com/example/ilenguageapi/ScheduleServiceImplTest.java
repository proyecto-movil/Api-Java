package com.example.ilenguageapi;
import com.example.ilenguageapi.domain.model.Schedule;
import com.example.ilenguageapi.domain.repository.ScheduleRepository;
import com.example.ilenguageapi.domain.service.ScheduleService;
import com.example.ilenguageapi.exception.ResourceNotFoundException;
import com.example.ilenguageapi.service.ScheduleServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class ScheduleServiceImplTest {
    @MockBean
    private ScheduleRepository _scheduleRepository;
    @Autowired
    private ScheduleService _scheduleService;

    @TestConfiguration
    static class ScheduleServiceImplConfiguration{
        @Bean
        public ScheduleService scheduleService(){return new ScheduleServiceImpl();}
    }

    @Test
    @DisplayName("Get schedule by Day with valid Day  then return true")
    public void whenGetScheduleByDayWithValidDayThenReturnsSchedule(){
        //Arrange
        String Day = "Monday";
        Schedule schedule = new Schedule();

        schedule.setDay(Day);

        when(_scheduleRepository.findByDay(Day))
                .thenReturn(Optional.of(schedule));
        //Act
        Schedule foundSchedule = _scheduleService.getByDay(Day);

        //Assert
        assertThat(foundSchedule.getDay()).isEqualTo(Day);
    }

    @Test
    @DisplayName("Get schedule error message when Day course is not valid")
    public void whenGetScheduleByDayWithInvalidNameReturnsResourceNotFoundException(){
        String Day ="Manday";
        String template ="Resource %s not found for %s with value %s";
        when(_scheduleRepository.findByDay(Day))
                .thenReturn(Optional.empty());
        String expectedMessage = String.format(template, "Schedule", "Day", Day);
        //Act
        Throwable exception = catchThrowable(()->{
            Schedule foundSchedule = _scheduleService.getByDay(Day);
        });

        //Assert
        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);
    }



}
