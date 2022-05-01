package com.soft2146.crm.api.config;

import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * @author Tao
 * 接口文档配置
 */
@Configuration
public class SwaggerConfig {
//    @Bean
//    public Docket createRestApi() {
//        Docket docket = new Docket(DocumentationType.SWAGGER_2) //文档类型，选择SWAGGER_2即可
//                // 配置映射路径
//                .pathMapping("/")
//                // 扫描Controller包路径，获得Api接口
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.scs.soft.school.api.school.spi.controller"))
//                .paths(PathSelectors.any())
//                // 配置Api接口信息
//                .build().apiInfo(new ApiInfoBuilder()
//                        .title("学生管理系统接口文档")
//                        .description("swagger-bootstrap-ui")
//                        .version("1.0")
//                        .contact(new Contact("Taoyox", "https://github.com/taoyongxin", "1427177855@qq.com"))// 联系人
//                        .license("The Apache License, Version 2.0")
//                        .licenseUrl("http://localhost:8082/")
//                        .build()
//                );
//        return docket;
//    }

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("客户管理系统在线接口文档。")
                .description("swagger-bootstrap-ui")
                .contact(new Contact("客户管理系统","https://github.com/taoyongxin","1427177855@qq.com"))
                .termsOfServiceUrl("http://localhost:8082/")
                .version("1.0")
                .build();
    }
}

