package com.lym.demo.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * 文档范文地址: http://localhost:8084/swagger-ui.html
 * Created by liuyanmin on 2019/8/27.
 */
@Configuration
@EnableSwagger2
@ConditionalOnExpression("${swagger.enable:true}")
public class SwaggerConfig {

    @Bean
    public Docket adminDocket() {


        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("admin")
                .apiInfo(apiInfo())
                .globalOperationParameters(parameters())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.lym.demo.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("后台API接口文档")
                .description("后台API接口文档")
                .version("1.0")
                .build();
    }

    private List<Parameter> parameters() {
        // 在请求头(header)中添加 userId 和 token 参数
        ParameterBuilder parameterBuilder = new ParameterBuilder();
        List<Parameter> parameters = new ArrayList<>();
        parameterBuilder.name("userId").description("用户ID")
                .modelRef(new ModelRef("string")).parameterType("header").required(false).build();

        ParameterBuilder parameterBuilder2 = new ParameterBuilder();
        parameterBuilder2.name("token").description("Token")
                .modelRef(new ModelRef("string")).parameterType("header").required(false).build();

        parameters.add(parameterBuilder.build());
        parameters.add(parameterBuilder2.build());

        return parameters;
    }

}
