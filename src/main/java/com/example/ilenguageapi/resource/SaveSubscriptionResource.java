package com.example.ilenguageapi.resource;

import org.hibernate.validator.constraints.CreditCardNumber;

import javax.validation.constraints.*;
import javax.validation.constraints.Positive;

public class SaveSubscriptionResource {
    @Positive(message ="Price must have a positive value")
    private float price;

    @NotNull
    @Positive(message ="Price must have a positive value")
    private int monthDuration;

    @NotNull
    @NotBlank
    @Size(min = 3, max = 20, message="Name must have between 3 and 20 characters")
    private String name;

    public float getPrice() {
        return price;
    }

    public int getMonthDuration() {
        return monthDuration;
    }

    public String getName() {
        return name;
    }

    public SaveSubscriptionResource setPrice(float price) {
        this.price = price;
        return this;
    }

    public SaveSubscriptionResource setMonthDuration(int monthDuration) {
        this.monthDuration = monthDuration;
        return this;
    }

    public SaveSubscriptionResource setName(String name) {
        this.name = name;
        return this;
    }
}
