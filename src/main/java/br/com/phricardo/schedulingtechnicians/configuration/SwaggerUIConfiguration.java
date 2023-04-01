package br.com.phricardo.schedulingtechnicians.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SwaggerUIConfiguration implements WebMvcConfigurer {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().info(new Info()
                .title("Scheduling Technicians API")
                .description("This API allows the registration of technicians and customers to schedule a technical\n" +
                        "visit at the customer's address.")
                .version("1.0.0")
                .contact(new Contact()
                        .url("https://www.phricardo.com.br/")
                        .email("contato@phricardo.com.br")
                )
        );
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addRedirectViewController("/", "/swagger-ui.html");
        registry.addRedirectViewController("/swagger-ui", "/swagger-ui.html");
        registry.addRedirectViewController("/swagger-ui/", "/swagger-ui.html");
    }
}
