package com.zed.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

@Configuration
public class ThymeleafConfiguration {

    /*<bean id="templateResolver"
     class="org.thymeleaf.templateresolver.ServletContextTemplateResolver">
     <property name="prefix" value="/WEB-INF/templates/" />
     <property name="suffix" value=".html" />
     <property name="templateMode" value="HTML5" />
     </bean>*/

    @Bean
    ServletContextTemplateResolver templateResolver() {
        ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver();
        templateResolver.setPrefix("/WEB-INF/templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode("HTML5");
        return templateResolver;
    }

    /*<bean id="templateEngine"
    class="org.thymeleaf.spring3.SpringTemplateEngine">
    <property name="templateResolver" ref="templateResolver" />
    </bean>*/

    @Bean
    SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        return templateEngine;
    }

    /*<bean class="org.thymeleaf.spring3.view.ThymeleafViewResolver">
    <property name="templateEngine" ref="templateEngine" />
    <property name="order" value="1" />
    <property name="viewNames" value="*.html,*.xhtml" />
    </bean>*/

    @Bean
    ThymeleafViewResolver viewResolver() {
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(templateEngine());
        viewResolver.setOrder(1);
//        viewResolver.setViewNames(new String[]{"*.html", "*.xhtml"});
        return viewResolver;
    }

}
