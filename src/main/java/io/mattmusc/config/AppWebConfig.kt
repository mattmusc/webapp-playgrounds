package io.mattmusc.config

import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import org.thymeleaf.spring5.ISpringTemplateEngine
import org.thymeleaf.spring5.SpringTemplateEngine
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver
import org.thymeleaf.spring5.view.ThymeleafViewResolver
import org.thymeleaf.templatemode.TemplateMode
import org.thymeleaf.templateresolver.ITemplateResolver

@EnableWebMvc
@Configuration
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
                .apply { isCacheable = false }
                .apply { setApplicationContext(applicationContext) }
    }
}
