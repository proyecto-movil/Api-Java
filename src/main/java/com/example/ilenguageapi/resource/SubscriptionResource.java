package com.example.ilenguageapi.resource;

public class SubscriptionResource {
    private int id;
    private String name;
    private float price;
    private int monthDuration;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    public int getMonthDuration() {
        return monthDuration;
    }

    public SubscriptionResource setId(int id) {
        this.id = id;
        return this;
    }

    public SubscriptionResource setName(String name) {
        this.name = name;
        return this;
    }

    public SubscriptionResource setPrice(float price) {
        this.price = price;
        return this;
    }

    public SubscriptionResource setMonthDuration(int monthDuration) {
        this.monthDuration = monthDuration;
        return this;
    }
}
