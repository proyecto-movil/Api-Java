package com.example.ilenguageapi;

import com.example.ilenguageapi.domain.model.Session;
import com.example.ilenguageapi.domain.repository.SessionRepository;
import com.example.ilenguageapi.domain.service.SessionService;
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

import java.util.Optional;

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
        public SessionService postService() {
            return new SessionServiceImpl();
        }
    }

    @Test
    @DisplayName("When Get Session By Start At With Valid Start At Then Returns Session")
    public void whenGetSessionByStartAtWithValidStartAtThenReturnsSession() {
        // Arrange
        String startAt = "10";
        Session session = new Session().setId(1).setStartAt(startAt);
        when(sessionRepository.findByStartAt(startAt))
                .thenReturn(Optional.of(session));

        // Act
        Session foundSession = sessionService.getSessionByStartAt(startAt);

        // Assert
        assertThat(foundSession.getStartAt()).isEqualTo(startAt);
    }

}
