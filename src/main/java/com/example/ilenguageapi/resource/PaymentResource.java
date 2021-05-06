package com.example.ilenguageapi.resource;

import com.example.ilenguageapi.domain.model.Currency;

public class PaymentResource {
    private String description;
    private Currency currency;
    private float amount;

    public String getDescription() {
        return description;
    }

    public PaymentResource setDescription(String description) {
        this.description = description;
        return this;
    }

    public Currency getCurrency() {
        return currency;
    }

    public PaymentResource setCurrency(Currency currency) {
        this.currency = currency;
        return this;
    }

    public float getAmount() {
        return amount;
    }

    public PaymentResource setAmount(float amount) {
        this.amount = amount;
        return this;
    }
}
