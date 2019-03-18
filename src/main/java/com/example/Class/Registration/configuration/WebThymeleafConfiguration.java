package com.example.Class.Registration.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.spring5.ISpringTemplateEngine;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ITemplateResolver;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;

import nz.net.ultraq.thymeleaf.LayoutDialect;

public class WebThymeleafConfiguration {

	 @Autowired private ApplicationContext applicationContext;
	    
	    @Autowired private MappingJackson2HttpMessageConverter jsonConverter;
	    
	    @Bean("springMvcTemplateResolver")
	    public ITemplateResolver templateResolver() {
	        final SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
	        resolver.setApplicationContext( applicationContext );
	        resolver.setPrefix( "/WEB-INF/views/" );
	        resolver.setSuffix( ".xhtml" );
	        resolver.setTemplateMode( TemplateMode.HTML );
	        resolver.setOrder( 1 );
	        return resolver;
	    }
	    
	    @Bean("springTemplateEngine")
	    public ITemplateEngine templateEngine() {
	        final SpringTemplateEngine engine = new SpringTemplateEngine();
	        engine.setTemplateResolver( templateResolver() );
	        engine.addDialect( new LayoutDialect() );
	        engine.addDialect( new SpringSecurityDialect() );
	        return engine;
	    }
	    
	    @Bean
	    public ThymeleafViewResolver thymeleafViewResolver() {
	        final ThymeleafViewResolver resolver = new ThymeleafViewResolver();
	        resolver.setTemplateEngine( (ISpringTemplateEngine) templateEngine() );
	        return resolver;
	    }
	    
	    @Bean
	    public RequestMappingHandlerAdapter requestMappingHandlerAdapter() {
	        final RequestMappingHandlerAdapter requestMappingHandlerAdapter = new RequestMappingHandlerAdapter();
	        requestMappingHandlerAdapter.getMessageConverters().add( jsonConverter );
	        return requestMappingHandlerAdapter;
	    }
}
