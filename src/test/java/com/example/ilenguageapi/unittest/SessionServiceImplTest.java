package com.example.ilenguageapi.unittest;

import com.example.ilenguageapi.domain.model.Session;
import com.example.ilenguageapi.domain.repository.SessionRepository;
import com.example.ilenguageapi.domain.service.SessionService;
import com.example.ilenguageapi.exception.ResourceNotFoundException;
import com.example.ilenguageapi.service.SessionServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Calendar;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class SessionServiceImplTest {

    @MockBean
    private SessionRepository sessionRepository;

    @Autowired
    private SessionService sessionService;

    @TestConfiguration
    static class SessionServiceImplTestConfiguration {
        @Bean
        public SessionService sessionService() {
            return new SessionServiceImpl();
        }
    }

    @Test
    @DisplayName("When getSessionByTopic With Valid topic Then Returns Session")
    public void whenGetSessionByTopicAtWithValidTopicAtThenReturnsSession() {
        // Arrange
        String topic = "active";
        Session session = new Session().setId(1).setTopic(topic);
        when(sessionRepository.findByTopic(topic))
                .thenReturn(Optional.of(session));

        // Act
        Session foundSession = sessionService.getSessionByTopic(topic);

        // Assert
        assertThat(foundSession.getTopic()).isEqualTo(topic);
    }

     /*
    @Test
    @DisplayName("When getSessionByStartAt With Invalid startAt Then Returns Session")
    public void whenGetSessionByStartAtWithInvalidStartAtThenReturnsSession() {
        // Arrange
        String startAt = "10:10/24-05-2021";
        String template = "Resource %s not found for %s with value %s";
        when(sessionRepository.findByStartAt(startAt))
                .thenReturn(Optional.empty());
        String expectedMessage = String.format(template, "Session", "StartAt", startAt);

        // Act
        Throwable exception = catchThrowable(() -> {
            Session foundSession = sessionService.getSessionByStartAt(startAt);
        });

        // Assert
        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);
    }


    @Test
    @DisplayName("When getSessionByEndAt With Valid endAt Then Returns Session")
    public void whenGetSessionByEndAtWithValidStartAtThenReturnsSession() {
        // Arrange
        String endAt = "10:40/24-05-2021";
        Session session = new Session().setId(1).setEndAt(endAt);
        when(sessionRepository.findByEndAt(endAt))
                .thenReturn(Optional.of(session));

        // Act
        Session foundSession = sessionService.getSessionByEndAt(endAt);

        // Assert
        assertThat(foundSession.getEndAt()).isEqualTo(endAt);
    }

    @Test
    @DisplayName("When getSessionByEndAt With Invalid endAt Then Returns Session")
    public void whenGetSessionByEndAtWithInvalidEndAtThenReturnsSession() {
        // Arrange
        String endAt = "10:40/24-05-2021";
        String template = "Resource %s not found for %s with value %s";
        when(sessionRepository.findByStartAt(endAt))
                .thenReturn(Optional.empty());
        String expectedMessage = String.format(template, "Session", "EndAt", endAt);

        // Act
        Throwable exception = catchThrowable(() -> {
            Session foundSession = sessionService.getSessionByEndAt(endAt);
        });

        // Assert
        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);
    }
    */
}
