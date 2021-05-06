package com.example.ilenguageapi.resource;

import javax.validation.constraints.*;
import javax.validation.constraints.Positive;

public class SaveSubscriptionResource {
    @Positive(message ="Price must have a positive value")
    private float Price;

    @NotNull
    @Positive(message ="Price must have a positive value")
    private int monthDuration;

    @NotBlank
    @Size(min = 3, max = 20, message="Name must have between 3 and 20 characters")
    private String name;

}
