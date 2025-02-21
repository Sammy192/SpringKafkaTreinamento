package br.com.rocha.apiboleto.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenApi() {
        return new OpenAPI().info(new Info()
                .title("API de Boleto")
                .description("API para pagamento de boletos")
                .contact(new Contact().name("Samuel Borges").email("teste@teste.com"))
                .version("1.0.0")
        );
    }
}
