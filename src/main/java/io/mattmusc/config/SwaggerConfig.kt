package io.mattmusc.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.service.Contact
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2
import java.util.Collections.emptyList

@Configuration
@EnableSwagger2
open class SwaggerConfig {
    @Value("\${appTitle:test}")
    private lateinit var appTitle: String

    @Bean
    open fun apiDocket(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .apiInfo(getApiInfo())
    }

    private fun getApiInfo(): ApiInfo {
        return ApiInfo(
                appTitle,
                "DESCIPRION",
                "VERSION",
                "TERMS OF SERVICE URL",
                Contact("NAME", "URL", "EMAIL"),
                "LICENSE",
                "LICENSE URL",
                emptyList()
        )
    }
}