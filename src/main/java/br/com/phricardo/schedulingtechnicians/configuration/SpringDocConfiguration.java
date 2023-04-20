package br.com.phricardo.schedulingtechnicians.configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SpringDocConfiguration implements WebMvcConfigurer {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().components(getComponents()).info(getInfo());
    }

    private Info getInfo() {
       return new Info().title("Scheduling Technicians API")
                .description("This API allows the registration of technicians and customers to schedule a technical\n" +
                        "visit at the customer's address.")
                .version("1.0.0")
                .contact(new Contact().url("https://www.phricardo.com.br/").email("contato@phricardo.com.br"));
    }

    private Components getComponents() {
        return new Components()
                .addSecuritySchemes("bearer-key",
                        new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT"));
    }


    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addRedirectViewController("/", "/swagger-ui.html");
        registry.addRedirectViewController("/swagger-ui", "/swagger-ui.html");
        registry.addRedirectViewController("/swagger-ui/", "/swagger-ui.html");
    }
}
