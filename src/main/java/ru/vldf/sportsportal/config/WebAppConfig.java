package ru.vldf.sportsportal.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;

@EnableWebMvc
@Configuration
@ComponentScan({
        "ru.vldf.sportsportal.config",
        "ru.vldf.sportsportal.controller",
        "ru.vldf.sportsportal.dao.impl",
        "ru.vldf.sportsportal.security",
        "ru.vldf.sportsportal.service"
})
public class WebAppConfig extends WebMvcConfigurerAdapter {

//    ==================================================================================
//    === THYMELEAF VIEW RESOLVER

    @Bean(name = "templateResolver")
    public ServletContextTemplateResolver getTemplateResolver() {
        ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver();
        templateResolver.setCharacterEncoding("UTF-8");
        templateResolver.setTemplateMode("HTML5");
        templateResolver.setPrefix("/views/");
        templateResolver.setSuffix(".html");

        return templateResolver;
    }

    @Bean(name = "templateEngine")
    public SpringTemplateEngine getTemplateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.addDialect(SpringSecurityDialect.DEFAULT_PREFIX, new SpringSecurityDialect());
        templateEngine.setTemplateResolver(getTemplateResolver());

        return templateEngine;
    }

    @Bean
    public ThymeleafViewResolver setupThymeleafViewResolver() {
        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
        resolver.setTemplateEngine(getTemplateEngine());
        resolver.setCharacterEncoding("UTF-8");

        return resolver;
    }

//    ==================================================================================
//    === RESOURCE HANDLERS

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/libs/**").addResourceLocations("/libs/");
        registry.addResourceHandler("/img/**").addResourceLocations("/resources/img/");
        registry.addResourceHandler("/css/**").addResourceLocations("/resources/css/");
        registry.addResourceHandler("/js/**").addResourceLocations("/resources/js/");
        registry.addResourceHandler("/components/**").addResourceLocations("/resources/js/components/");
    }
}