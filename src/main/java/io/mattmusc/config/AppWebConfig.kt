package io.mattmusc.config

import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import org.thymeleaf.spring5.ISpringTemplateEngine
import org.thymeleaf.spring5.SpringTemplateEngine
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver
import org.thymeleaf.spring5.view.ThymeleafViewResolver
import org.thymeleaf.templatemode.TemplateMode
import org.thymeleaf.templateresolver.ITemplateResolver
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2


@EnableWebMvc
@Configuration
@EnableSwagger2
@ComponentScan("io.mattmusc.controller")
open class ApplicationWebConfig : ApplicationContextAware, WebMvcConfigurer {

    private lateinit var applicationContext: ApplicationContext

    override fun setApplicationContext(applicationContext:
                                       ApplicationContext) {
        this.applicationContext = applicationContext
    }

    override fun configureViewResolvers(registry: ViewResolverRegistry) {
        registry.viewResolver(ThymeleafViewResolver()
                .apply { templateEngine = templateEngine() })
    }

    override fun addResourceHandlers(registry: ResourceHandlerRegistry?) {
        registry?.addResourceHandler("swagger-ui.html")
                ?.addResourceLocations("classpath:/META-INF/resources/")

        registry?.addResourceHandler("/webjars/**")
                ?.addResourceLocations("classpath:/META-INF/resources/webjars/")
    }

    @Bean
    open fun api(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
    }

    private fun templateEngine(): ISpringTemplateEngine? {
        return SpringTemplateEngine()
                .apply { enableSpringELCompiler = true }
                .apply { templateResolvers = setOf(htmlTemplatesResolver()) }
    }

    private fun htmlTemplatesResolver(): ITemplateResolver? {
        return SpringResourceTemplateResolver()
                .apply { prefix = "/views/" }
                .apply { suffix = ".html" }
                .apply { templateMode = TemplateMode.HTML }
                .apply { setApplicationContext(applicationContext) }
    }
}
