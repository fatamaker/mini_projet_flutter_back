package com.fatma.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Maps the "/images/**" path to the location of your static images
    	registry.addResourceHandler("/images/**")
        .addResourceLocations("file:" + System.getProperty("user.dir") + "/uploads/");
    }
    
    
 
}
