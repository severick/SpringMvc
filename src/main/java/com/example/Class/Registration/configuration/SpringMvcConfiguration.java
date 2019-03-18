package com.example.Class.Registration.configuration;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import com.fasterxml.jackson.databind.ObjectMapper;

@EnableWebMvc
@PropertySource(value = {"classpath:application.properties" })
@ComponentScan(basePackages = {"com.example.Class.Registration"})
@Configuration
public class SpringMvcConfiguration extends WebMvcConfigurerAdapter {

    @Autowired private ObjectMapper jsonObjectMapper;
    
    private static final int DAYS_IN_A_WEEK = 7;
    private static final int MAX_UPLOAD_SIZE = 20971520;
    private static final int MAX_IN_MEMORY_SIZE = 1048576;
    
    @Bean
    public static PropertySourcesPlaceholderConfigurer placeHolderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
       
    /**
     * Enable public access to static resources.
     */
    @Override
    public void addResourceHandlers( ResourceHandlerRegistry registry ) {
        registry.addResourceHandler( "/scripts/**" ).addResourceLocations( "classpath:/static/scripts/" ).setCacheControl( CacheControl.maxAge( DAYS_IN_A_WEEK, TimeUnit.DAYS ) );
        registry.addResourceHandler( "/styles/**" ).addResourceLocations( "classpath:/static/styles/" ).setCacheControl( CacheControl.maxAge( DAYS_IN_A_WEEK, TimeUnit.DAYS ) );
        registry.addResourceHandler( "/images/**" ).addResourceLocations( "classpath:/static/images/" ).setCacheControl( CacheControl.maxAge( DAYS_IN_A_WEEK, TimeUnit.DAYS ) );
        registry.addResourceHandler( "/help/**" ).addResourceLocations( "classpath:/static/help/" ).setCacheControl( CacheControl.maxAge( DAYS_IN_A_WEEK, TimeUnit.DAYS ) );
    }
}
