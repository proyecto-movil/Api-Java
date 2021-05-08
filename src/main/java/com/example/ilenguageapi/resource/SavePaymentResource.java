package com.example.ilenguageapi.resource;

import com.example.ilenguageapi.domain.model.Currency;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

public class SavePaymentResource {

    @Positive(message="Amount must have a positive value")
    private float amount;

    private Currency currency;

    @NotBlank
    @Size(max = 30, message="Description musty have maximun 30 characters")
    private String description;


    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
