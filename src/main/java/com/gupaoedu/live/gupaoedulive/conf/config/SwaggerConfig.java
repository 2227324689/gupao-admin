package com.gupaoedu.live.gupaoedulive.conf.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * Swagger2的接口配置
 * 
 * @author ruoyi
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig
{
    /**
     * 创建API
     */
    @Bean
    public Docket createRestApi()
    {
        //添加head参数start
        List<Parameter> pars = new ArrayList<Parameter>();
        ParameterBuilder apiToken = new ParameterBuilder();
        apiToken.name("apiToken").description("令牌").modelRef(new ModelRef("string")).defaultValue("f54c0e49c0314c06b558f3d518cdefae").parameterType("header").required(false).build();
        ParameterBuilder tenantCode = new ParameterBuilder();
        tenantCode.name("tenantCode").description("租户编码").modelRef(new ModelRef("string")).defaultValue("zc").parameterType("header").required(false).build();
        pars.add(apiToken.build());
        pars.add(tenantCode.build());
        //添加head参数end

        return new Docket(DocumentationType.SWAGGER_2)
                // 详细定制
                .apiInfo(apiInfo())
                .select()
                // 指定当前包路径
//                .apis(RequestHandlerSelectors.basePackage("com.ruoyi.web.controller.tool"))
//                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                // 扫描所有 z
                 .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(pars);
    }

    /**
     * 添加摘要信息
     */
    private ApiInfo apiInfo()
    {
        // 用ApiInfoBuilder进行定制
        return new ApiInfoBuilder()
                .title("标题：gupaolive_接口文档")
                .description("描述：gupaolive")
                .contact(new Contact("gupao", null, null))
                .version("版本号:1")
                .build();
    }
}
