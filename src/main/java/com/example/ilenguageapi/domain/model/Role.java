package com.example.ilenguageapi.domain.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name="Roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max=20)
    @NaturalId
    public String name;

    @OneToMany(mappedBy = "role",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<User> users;

    public Role addUser(User user){
       getUsers().add(user);
       return this;
    }

    public Role() {
    }

    public Role( @NotNull String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public Role setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Role setName(String name) {
        this.name = name;
        return this;
    }

    public List<User> getUsers() {
        return this.users;
    }

    public Role setUsers(List<User> users) {
        this.users = users;
        return this;
    }
}
