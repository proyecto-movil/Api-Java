package com.example.ilenguageapi.configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfiguration {
    @Bean(name ="iLanguageOpenApi")
    public OpenAPI iLanguageOpenApi(){
        return new OpenAPI()
                .components(new Components())
                .info(new Info()
                .title("Ilanguage Aplication api")
                 .description("ILanguage API implemmented with Spring Boot RESTful service and docummented using springdoc-openapi-ui 3.0"));

    }
}
