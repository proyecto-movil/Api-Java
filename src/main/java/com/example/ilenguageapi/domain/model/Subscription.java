package com.example.ilenguageapi.domain.model;

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

    @Positive(message="Price must have a positive value")
    public float Price;

    @NotNull
    @Min(value=1, message="Month duration shold not be less tah 1")
    @Max(value=12, message="Month duration should no be grater than 12")
    public int MonthDuration;

    @NotBlank(message ="Name is mandatory")
    @Size(min = 3, max = 20, message = "Name must be between 3 and 20 characters")
    public String Name;


    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "user_subscriptions",
            joinColumns = {@JoinColumn(name="subscription_id")},
            inverseJoinColumns = {@JoinColumn(name="user_id")})
    private List<User> users;



    public Subscription() {
    }

    public Subscription(int id, float price, int monthDuration, String name, List<User> users) {
        this.id = id;
        Price = price;
        MonthDuration = monthDuration;
        Name = name;
        this.users = users;
    }


    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getPrice() {
        return Price;
    }

    public void setPrice(float price) {
        Price = price;
    }

    public int getMonthDuration() {
        return MonthDuration;
    }

    public void setMonthDuration(int monthDuration) {
        MonthDuration = monthDuration;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }


}
