package com.example.ilenguageapi;

import com.example.ilenguageapi.domain.model.SessionDetail;
import com.example.ilenguageapi.domain.repository.SessionDetailRepository;
import com.example.ilenguageapi.domain.service.SessionDetailService;
import com.example.ilenguageapi.exception.ResourceNotFoundException;
import com.example.ilenguageapi.service.SessionDetailServiceImpl;
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
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class SessionDetailServiceImplTest {

    @MockBean
    private SessionDetailRepository sessionDetailRepository;

    @Autowired
    private SessionDetailService sessionDetailService;

    @TestConfiguration
    static class SessionDetailServiceImplTestConfiguration {
        @Bean
        public SessionDetailService sessionDetailServiceService() {
            return new SessionDetailServiceImpl();
        }
    }

    @Test
    @DisplayName("When getSessionDetailByState With Valid state Then Returns SessionDetail")
    public void whenGetSessionByStateWithValidStateThenReturnsState() {
        // Arrange
        String state = "active";
        SessionDetail sessionDetail = new SessionDetail().setId(1).setState(state);
        when(sessionDetailRepository.findByState(state))
                .thenReturn(Optional.of(sessionDetail));

        // Act
        SessionDetail foundSessionDetail = sessionDetailService.getSessionDetailByState(state);

        // Assert
        assertThat(foundSessionDetail.getState()).isEqualTo(state);
    }

    @Test
    @DisplayName("When getSessionByStartAt With Invalid startAt Then Returns Session")
    public void whenGetSessionByStartAtWithInvalidStartAtThenReturnsSession() {
        // Arrange
        String state = "active";
        String template = "Resource %s not found for %s with value %s";
        when(sessionDetailRepository.findByState(state))
                .thenReturn(Optional.empty());
        String expectedMessage = String.format(template, "SessionDetail", "State", state);

        // Act
        Throwable exception = catchThrowable(() -> {
            SessionDetail foundSessionDetail = sessionDetailService.getSessionDetailByState(state);
        });

        // Assert
        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);
    }
}
