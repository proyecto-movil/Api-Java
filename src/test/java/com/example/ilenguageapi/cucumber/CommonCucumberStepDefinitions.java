package com.example.ilenguageapi.cucumber;

import io.cucumber.java.en.Given;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

public class CommonCucumberStepDefinitions {
    @Autowired
    private HttpClient httpClient;

    @Given("^the bag is empty$")
    public void the_bag_is_empty() {
        httpClient.clean();
        //assertThat(bagHttpClient.getContents().isEmpty()).isTrue();
    }
}
