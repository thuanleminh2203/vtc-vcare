package com.venesa.ctvvcare.config;

/**
 * @author thuanlm
 * @created at 11/3/2020
 */
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebMvcConfig implements WebMvcConfigurer {
    /**
     * Configure ResourceHandlers to serve static resources like CSS/ Javascript
     * etc...
     */

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    	registry.addResourceHandler( "/img/**","/css/**","/js/**", "/fonts/**", "style.css")
    	.addResourceLocations("classpath:/static/img/", "classpath:/static/css/","classpath:/static/js/", "classpath:/static/fonts/",  "classpath:/static/style.css");

        registry.addResourceHandler( "/**")
                .addResourceLocations("classpath:/templates/");
    }
}