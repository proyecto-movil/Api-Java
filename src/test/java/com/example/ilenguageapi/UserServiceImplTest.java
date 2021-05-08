package com.example.ilenguageapi;

import com.example.ilenguageapi.domain.model.User;
import com.example.ilenguageapi.domain.repository.UserRepository;
import com.example.ilenguageapi.domain.service.UserService;
import com.example.ilenguageapi.exception.ResourceNotFoundException;
import com.example.ilenguageapi.service.UserServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.swing.text.html.Option;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class UserServiceImplTest {

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserService userService;


    @TestConfiguration
    static class UserServiceImplTestConfiguration {
        @Bean
        public UserService userService() {
            return new UserServiceImpl();
        }
    }

    @Test
    @DisplayName("When getUserById With Valid Id Then Returns User")
    public void whenGetUserByUserIdWithValidIdThenReturnsUser() {
        //Arrange
        Long userId = 12L;
        User user = new User().setId(userId).setName("pepe");
        when(userRepository.findById(userId))
                .thenReturn(Optional.of(user));
        //Act
        User foundUser = userService.getUserById(userId);
        //Assert
        assertThat(foundUser.getId()).isEqualTo(userId);
    }

    @Test
    @DisplayName("When getUserById With Invalid Id Then Returns ResourceNotFoundExceptions")
    public void whenGetUserByIdWithInvalidIdThenReturnsResourceNotFoundExceptions() {
        //Arrange
        Long userId = 11L;
        String template = "Resource %s not found for %s with value %s";
        when(userRepository.findById(userId))
                .thenReturn(Optional.empty());
        String expectedMessage = String.format(template, "User", "id", userId);
        //Act
        Throwable exception = catchThrowable(() -> {
            User foundUser = userService.getUserById(userId);
        });
        //Assert
        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);
    }

    @Test
    @DisplayName("When DeletedUserById With inValid Id Then Returns ResourceNotFoundExceptions")
    public void whenDeletedUserByIdWithInvalidIdThenReturnResourceNotFoundExceptions() {
        //Arrange
        Long userId = 11L;
        String template = "Resource %s not found for %s with value %s";
        when(userRepository.findById(userId))
                .thenReturn(Optional.empty());
        String expectedMessage = String.format(template, "User", "id", userId);
        //Act
        Throwable exception = catchThrowable(() -> {
            ResponseEntity<?> deletedUser = userService.deleteUser(userId);
        });
        //Assert
        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);
    }
}
