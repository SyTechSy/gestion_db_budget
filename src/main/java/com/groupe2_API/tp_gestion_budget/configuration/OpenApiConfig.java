package com.groupe2_API.tp_gestion_budget.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI apiBudgetOpenApi() {
        return new OpenAPI()
        .info(new Info().title("Budget APi")
                .description("Une Api de budget pour gerer les depences a travers leur categorie")
                .version("1.0.0")
        );
    }

}
