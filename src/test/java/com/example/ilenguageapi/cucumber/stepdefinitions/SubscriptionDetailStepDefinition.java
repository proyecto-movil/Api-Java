package com.example.ilenguageapi.cucumber.stepdefinitions;

import com.example.ilenguageapi.cucumber.configuration.SpringIntegrationTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import javassist.compiler.ast.MethodDecl;
import org.springframework.http.HttpStatus;
//import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.assertj.core.api.Assertions.assertThat;

public class SubscriptionDetailStepDefinition extends SpringIntegrationTest {


    @Given("the client is on the main page")
    public void theClientIsOnTheMainPage()throws Throwable {
    }

    @When("the client searches for a subscription {int}")
    public void theClientSearchesForASubscription(int arg0) throws Throwable{
        executeGet("http://localhost:8080/subscriptions/1");
    }

    @Then("the client receives subscription {int} details")
    public void theClientReceivesSubscriptionDetails(int arg0)throws Throwable {
        final HttpStatus currentStatusCode = latestResponse.getTheResponse().getStatusCode();
        assertThat(currentStatusCode.value()).isEqualTo(200);
    }
}
