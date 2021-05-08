package com.example.ilenguageapi;

import com.example.ilenguageapi.domain.model.Role;
import com.example.ilenguageapi.domain.model.User;
import com.example.ilenguageapi.domain.repository.RoleRepository;
import com.example.ilenguageapi.domain.service.RoleService;
import com.example.ilenguageapi.exception.ResourceNotFoundException;
import com.example.ilenguageapi.service.RoleServiceImpl;
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
public class RoleServiceImplTest {
    @MockBean
    private RoleRepository roleRepository;
    @Autowired
    private RoleService roleService;

    @TestConfiguration
    static class RoleServiceImplTestConfiguration{
        @Bean
        public RoleService roleService(){
            return new RoleServiceImpl();
        }
    }

    //CORRECT
    @Test
    @DisplayName("when GetRoleById With Valid Id Then ReturnsRole")
    public  void whenGetRoleByIdWithValidIdThenReturnsRole(){
        //Arrange
        Long id = 1L;
        Role role=new Role();
        role.setId(id);
        when(roleRepository.findById(id))
                .thenReturn(Optional.of(role));
        //Act
        Role foundRole=roleService.getRoleById(id);
        //Assert
        assertThat(foundRole.getId()).isEqualTo(id);
    }

    @Test
    @DisplayName("when GetRoleById With Invalid Id Then Returns ResourceNotFoundException")
    public  void whenGetRoleByIdWithInvalidIdThenReturnsResourceNotFoundException(){
        //Arrange
        Long id = 1L;
        String template="Resource %s not found for %s with value %s";
        when(roleRepository.findById(id))
                .thenReturn(Optional.empty());
        String expectedMessage=String.format(template,"Role","id",id);

        //Act
        Throwable exception=catchThrowable(()->{
            Role foundPost=roleService.getRoleById(id);
        });
        //Assert
        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);
    }



}
