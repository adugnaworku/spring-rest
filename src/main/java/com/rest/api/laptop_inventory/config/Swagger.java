package com.rest.api.laptop_inventory.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;

@Configuration
@EnableSwagger2
public class Swagger {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build( )
                .apiInfo(getApiInfo())
                .securitySchemes(Arrays.asList(apiKey()));
    }

    private ApiInfo getApiInfo() {
        return new ApiInfoBuilder()
                .title("Laptop inventory API")
                .version("1.0")
                .description("API for managing Contact.")
                .contact(new Contact("Adugna worku",
                        "http://adugna.myfakeurl.com",
                        "adugna.worku@gmail.com"))
                .license("Apache License Version 2.0")
                .build();

    }


    private ApiKey apiKey() {
        return new ApiKey("jwtToken", "Authorization", "header");
    }
}