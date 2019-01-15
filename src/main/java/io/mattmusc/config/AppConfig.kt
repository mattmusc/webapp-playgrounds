package io.mattmusc.config

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer

open class AppConfig : AbstractAnnotationConfigDispatcherServletInitializer() {
    override fun getRootConfigClasses(): Array<Class<*>>? {
        return emptyArray()
    }

    override fun getServletMappings(): Array<String> {
        return arrayOf("/")
    }

    override fun getServletConfigClasses(): Array<Class<*>>? {
        return arrayOf(ApplicationWebConfig::class.java)
    }

}
