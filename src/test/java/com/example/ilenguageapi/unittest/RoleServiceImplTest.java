package com.example.ilenguageapi.unittest;

import com.example.ilenguageapi.domain.model.Role;
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
import org.springframework.http.ResponseEntity;
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
    static class RoleServiceImplTestConfiguration {
        @Bean
        public RoleService roleService() {
            return new RoleServiceImpl();
        }
    }

    @Test
    @DisplayName("when GetRoleById With Valid Id Then ReturnsRole")
    public void whenGetRoleByIdWithValidIdThenReturnsRole() {
        //Arrange
        Long id = 1L;
        Role role = new Role();
        role.setId(id);
        when(roleRepository.findById(id))
                .thenReturn(Optional.of(role));
        //Act
        Role foundRole = roleService.getRoleById(id);
        //Assert
        assertThat(foundRole.getId()).isEqualTo(id);
    }

    @Test
    @DisplayName("when GetRoleById With Invalid Id Then Returns ResourceNotFoundException")
    public void whenGetRoleByIdWithInvalidIdThenReturnsResourceNotFoundException() {
        //Arrange
        Long id = 2L;
        String template = "Resource %s not found for %s with value %s";
        when(roleRepository.findById(id))
                .thenReturn(Optional.empty());
        String expectedMessage = String.format(template, "Role", "Id", id);

        //Act
        Throwable exception = catchThrowable(() -> {
            Role foundPost = roleService.getRoleById(id);
        });
        //Assert
        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);
    }

    @Test
    @DisplayName("when GetRoleByName With Valid Name Then Returns Role")
    public void whenGetRoleByNameWithValidNameThenReturnsRole() {
        //Arrange
        String name = "Teacher";
        Role role = new Role();
        role.setName(name);
        when(roleRepository.findByName(name))
                .thenReturn(Optional.of(role));
        //Act
        Role foundRole = roleService.getRoleByName(name);
        //Assert
        assertThat(foundRole.getName()).isEqualTo(name);
    }

    @Test
    @DisplayName("when GetRoleByName With Invalid Name Then Returns ResourceNotFoundException")
    public void whenGetRoleByNameWithInvalidNameThenReturnsResourceNotFoundException() {

        //Arrange
        String name = "Student";
        String template = "Resource %s not found for %s with value %s";
        when(roleRepository.findByName(name))
                .thenReturn(Optional.empty());
        String expectedMessage = String.format(template, "Role", "name", name);
        //Act
        Throwable exception = catchThrowable(() -> {
            Role foundName = roleService.getRoleByName(name);
        });
        //Assert
        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);
    }

    @Test
    @DisplayName("when DeletedRoleById With Invalid Id Then ReturnResourceNotFoundExceptions")
    public void whenDeletedRoleByIdWithInvalidIdThenReturnResourceNotFoundExceptions() {
        //Arrange
        Long id = 10L;
        String template = "Resource %s not found for %s with value %s";
        when(roleRepository.findById(id))
                .thenReturn(Optional.empty());
        String expectedMessage = String.format(template, "Role", "Id", id);
        //Act
        Throwable exception = catchThrowable(() -> {
            ResponseEntity<?> deletedRole = roleService.deleteRole(id);
        });
        //Assert
        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);
    }

    @Test
    @DisplayName("when UpdateRoleById With Valid Id Then Return UpdatedRole")
    public void whenUpdateRoleByIdWithValidIdThenReturnUpdatedRole() {
        //Arrange
        Long id = 2L;
        Role role = new Role();
        role.setName("Student");
        role.setId(id);
        Role newRole = new Role();
        newRole.setName("Teacher");
        newRole.setId(id);

        when(roleRepository.findById(id)).thenReturn(Optional.of(role));
        when(roleRepository.save(newRole)).thenReturn(newRole);
        //Act
        Role foundRole = roleService.updateRole(id, newRole);
        //Assert
        assertThat(role).isNotEqualTo(foundRole);
    }

    @Test
    @DisplayName("when UpdateRoleById With Invalid Id Then Return ResourceNotFoundExceptions")
    public void whenUpdateRoleByIdWithInvalidIdThenReturnResourceNotFoundExceptions() {
        //Arrange
        Long id = 14L;
        Role role = new Role();
        String template = "Resource %s not found for %s with value %s";
        when(roleRepository.findById(id))
                .thenReturn(Optional.empty());
        String expectedMessage = String.format(template, "Role", "Id", id);
        //Act
        Throwable exception = catchThrowable(() -> {
            Role updatedRole = roleService.updateRole(id, role);
        });
        //Assert
        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);
    }
     @Test
    @DisplayName("when Created RoleByRole With Valid Role Then Returns Role")
    public void whenCreatedRoleByRoleWithValidRoleThenReturnsRole() {
        //Arrange
        Role role = new Role();
        when(roleRepository.save(role)).thenReturn(role);
        //Act
        Role foundRole = roleService.createRole(role);
        //Assert
        assertThat(role)
                .isEqualTo(foundRole);
    }
}