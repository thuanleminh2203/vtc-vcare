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
        registry.addResourceHandler( "/**")
                .addResourceLocations("classpath:/static/**");
        registry.addResourceHandler("/resources/**")
                .addResourceLocations("/resources/");
    }
}