package com.itau.br.app.producer;

import com.itau.br.app.config.Parameter;
import com.itau.br.app.service.ParameterService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerProducer {

    private static ApiInfo apiInfo() {
        return new ApiInfo("Transfer API", "This Api was built to work with resources to make transfers.",
                "1.0.0-SNAPSHOT", "Terms of service",
                new Contact("Luiz Otavio", "", "luizotaviomartinsf@gmail.com"),
                "License of API", "API license URL", Collections.emptyList());
    }

    @Bean
    public Docket api() {
        Tag parameterController = new Tag("parameter-controller", "Operations pertaining to work with features");
        Tag healCheckController = new Tag("health-check-controller", "Operations pertaining to application health");

        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.itau.br.app.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo())
                .tags(parameterController, healCheckController);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public ParameterService service() {
        return new ParameterService() {
            @Override
            public Parameter getParameter(String featureName) {
                return new Parameter(featureName);
            }
        };
    }
}
