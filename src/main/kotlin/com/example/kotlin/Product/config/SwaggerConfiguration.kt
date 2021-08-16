package com.example.kotlin.Product.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import java.sql.Date
import java.time.LocalDate
import java.time.LocalTime


@Configuration
class SwaggerConfiguration {

    @Value("\${project.basePackage}")
    private val basePackage: String? = null


    @Bean
    fun swagger(): Docket? {
        return Docket(DocumentationType.SWAGGER_2).directModelSubstitute(LocalDate::class.java, Date::class.java)
            .directModelSubstitute(LocalTime::class.java, String::class.java).apiInfo(apiInfo()).select()
            .apis(RequestHandlerSelectors.basePackage(basePackage)).build()
    }

    private fun apiInfo(): ApiInfo? {
        return ApiInfoBuilder().title(basePackage).description("Learning Kotlin").version("0.0.1")
            .build()
    }
}