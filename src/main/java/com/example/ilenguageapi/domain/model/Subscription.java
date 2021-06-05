package com.example.ilenguageapi.domain.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.validator.constraints.CreditCardNumber;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;


@Entity
@Table(name ="subscriptions")
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @NotNull
    public int price;

    @NotNull
    public int monthDuration;

    @NotNull
    @NotBlank(message ="Name is mandatory")
    public String name;


    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY, mappedBy ="subscription")
    private List<UserSubscription> users;



    public Subscription() {
    }

    public Subscription(int id, int price, int monthDuration, String name, List<UserSubscription> users) {
        this.id = id;
        this.price = price;
        this.monthDuration = monthDuration;
        this.name = name;
        this.users = users;
    }


    public List<UserSubscription> getUsers() {
        return users;
    }

    public void setUsers(List<UserSubscription> users) {
        this.users = users;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getMonthDuration() {
        return monthDuration;
    }

    public void setMonthDuration(int monthDuration) {
        this.monthDuration = monthDuration;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
