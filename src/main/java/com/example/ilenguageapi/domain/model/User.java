package com.example.ilenguageapi.domain.model;

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

    public User() {
    }

    public User(@NotNull String name, @NotNull String lastName, @NotNull String email, @NotNull String password, @NotNull String description) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.description = description;
    }
    public boolean isUserWithRole(){
        return true;
    }
    public boolean isSubscribedWith(Subscription subscription){
       return this.getSubscriptions().contains(subscription);
    }

    public User SubscribedWith(Subscription subscription){
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

    //TODO:Implement Role
/*
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "role_id",nullable = false)
    JsonIgnore
    private Role role;
*/

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Subscription> getSubscriptions() {
        return subscriptions;
    }

    public void setSubscriptions(List<Subscription> subscriptions) {
        this.subscriptions = subscriptions;
    }

    public String getProfilePhoto() {
        return profilePhoto;
    }

    public void setProfilePhoto(String profilePhoto) {
        this.profilePhoto = profilePhoto;
    }
}
