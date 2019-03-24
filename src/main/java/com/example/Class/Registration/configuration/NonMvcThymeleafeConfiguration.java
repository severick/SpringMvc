package com.example.Class.Registration.configuration;

import java.util.Arrays;
import java.util.Collections;
import java.util.TreeSet;

import org.apache.commons.lang3.CharEncoding;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ITemplateResolver;
import org.thymeleaf.templateresolver.StringTemplateResolver;

import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor(onConstructor_ = { @Autowired })
public class NonMvcThymeleafeConfiguration {

	private ApplicationContext applicationContext;
	
    @Bean("nonMvcMessageSource")
    public ResourceBundleMessageSource nonMvcMessageSource() {
        final ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.addBasenames( "messages", "reports-messages" );
        messageSource.setAlwaysUseMessageFormat( true );
        return messageSource;
    }
    
    @Bean("nonMvcTextTemplateResolver")
    public ITemplateResolver nonMvcTextTemplateResolver() {
        final SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
        resolver.setApplicationContext( applicationContext );
        resolver.setPrefix( "/WEB-INF/" );
        resolver.setSuffix( ".txt" );
        resolver.setTemplateMode( TemplateMode.TEXT );
        resolver.setOrder( 1 );
        resolver.setResolvablePatterns( Collections.unmodifiableSet( new TreeSet<>( Arrays.asList( "mail/text/*", "reports/text/*", "messages/text/*" ) ) ) );
        resolver.setCharacterEncoding( CharEncoding.UTF_8 );
        resolver.setCacheable( false );
        return resolver;
    }
    
    @Bean("nonMvcHtmlTemplateResolver")
    public ITemplateResolver nonMvcHtmlTemplateResolver() {
        final SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
        resolver.setApplicationContext( applicationContext );
        resolver.setPrefix( "/WEB-INF/" );
        resolver.setSuffix( ".xhtml" );
        resolver.setTemplateMode( TemplateMode.HTML );
        resolver.setOrder( 2 );
        resolver.setResolvablePatterns( Collections.unmodifiableSet( new TreeSet<>( Arrays.asList( "mail/html/*", "reports/html/*", "messages/html/*" ) ) ) );
        resolver.setCharacterEncoding( CharEncoding.UTF_8 );
        resolver.setCacheable( false );
        return resolver;
    }
    
    @Bean("nonMvcStringTemplateResolver")
    public ITemplateResolver nonMvcStringTemplateResolver() {
        final StringTemplateResolver resolver = new StringTemplateResolver();
        resolver.setTemplateMode( TemplateMode.HTML );
        resolver.setOrder( 3 );
        resolver.setCacheable( false );
        return resolver;
    }
    
    @Bean("nonMvcTemplateEngine")
    public ITemplateEngine templateEngine() {
        final SpringTemplateEngine engine = new SpringTemplateEngine();
        engine.addTemplateResolver( nonMvcTextTemplateResolver() );
        engine.addTemplateResolver( nonMvcHtmlTemplateResolver() );
        engine.addTemplateResolver( nonMvcStringTemplateResolver() );
        engine.setTemplateEngineMessageSource( nonMvcMessageSource() );
        return engine;
    }
}
