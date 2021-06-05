package com.example.ilenguageapi.cucumber.stepdefinitions;

import com.example.ilenguageapi.domain.repository.SubscriptionRepository;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

public class ShowSubscriptionDetailsStepDefinitions {

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Given("the client is on the main page")
    public void theClientIsOnTheMainPage() {
    }

    @When("the client searches for a subscription {int}")
    public void theClientSearchesForASubscription(int subscriptionId) {
        var subscriptionSearched = subscriptionRepository.findById(subscriptionId);
    }

    @Then("the client recieves subscription {int} details")
    public void theClientRecievesSubscriptionDetails(int subscriptionId) {

    }
}
