package com.example.ilenguageapi.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 50)
    private String name;

    @NotNull
    @Size(max = 50)
    private String lastName;

    @NotNull
    @Column(unique = true)
    private String email;

    @NotNull
    private String password;

    @NotNull
    @Lob
    @Size(max = 245)
    private String description;

    @NotNull
    private String profilePhoto;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            mappedBy = "users")
    private List<Subscription> subscriptions;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn( name = "role_id", nullable = false)
    @JsonIgnore
    private Role role;

    public User() {
    }

    public User(@NotNull String name, @NotNull String lastName, @NotNull String email, @NotNull String password, @NotNull String description, @NotNull String profilePhoto) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.description = description;
        this.profilePhoto = profilePhoto;
    }
    public boolean isUserWithRole(String roleName){
        return getRole().name.equals(roleName);
    }
    public boolean isSubscribedWith(Subscription subscription){
       return this.getSubscriptions().contains(subscription);
    }

    public User SubscribeWith(Subscription subscription){
       if(!isSubscribedWith(subscription)){
          this.getSubscriptions().add(subscription);
       }
       return this;
    }
    public Subscription getSubcriptionActive(){
       int size = this.getSubscriptions().size();
       return this.getSubscriptions().get(size - 1);
    }

    //TODO: How to implement list of interest
    //private List<interest> interests;
    //private List<languageInterest> languageInterests;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public User setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public User setDescription(String description) {
        this.description = description;
        return this;
    }

    public List<Subscription> getSubscriptions() {
        return subscriptions;
    }

    public User setSubscriptions(List<Subscription> subscriptions) {
        this.subscriptions = subscriptions;
        return this;
    }

    public String getProfilePhoto() {
        return profilePhoto;
    }

    public User setProfilePhoto(String profilePhoto) {
        this.profilePhoto = profilePhoto;
        return this;
    }

    public Role getRole() {
        return role;
    }

    public User setRole(Role role) {
        this.role = role;
        return this;
    }
}
