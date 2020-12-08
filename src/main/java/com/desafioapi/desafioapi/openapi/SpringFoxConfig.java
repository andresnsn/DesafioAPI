package com.desafioapi.desafioapi.openapi;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SpringFoxConfig implements WebMvcConfigurer {

    @Bean
    public Docket apiDocket(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo()).tags(new Tag("Clientes", "Ver Detalhes"),
                        new Tag("Fornecedores", "Ver Detalhes"),
                         (new Tag("Produtos", "Ver Detalhes")), (new Tag("Vendas", "Ver Detalhes")))
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.desafioapi.desafioapi"))
                .build();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");


    }

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder().title("Documentação Desafio API")
                .title("Documentação do desafio de desenvolvimento de API")
                .description("Detalhes sobre todos os ENDPOINTS e funções da API estão contidos aqui.")
                .version("V1.0")
                .contact(new Contact("Sinka", "sinka.com.br", "sinka@sinka.com.br"))
                .build();
    }
}
