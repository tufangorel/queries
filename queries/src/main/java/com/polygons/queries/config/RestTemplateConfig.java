package com.polygons.queries.config;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

	
    @Bean
    @Qualifier("customRestTemplateCustomizer")
    public CustomRestTemplateCustomizer customRestTemplateCustomizer() {
        return new CustomRestTemplateCustomizer();
    }

    @Bean
    @DependsOn(value = {"customRestTemplateCustomizer"})
    public RestTemplateBuilder restTemplateBuilder() {
        return new RestTemplateBuilder(customRestTemplateCustomizer());
    }
    
    @Bean
    public List<HttpMessageConverter<?>> messageConverter() {
		List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
		messageConverters.add(converter);
		return messageConverters;
    }
    
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder
				.errorHandler(new RestTemplateResponseErrorHandler())
				.messageConverters(messageConverter())
				.build();
	}
	
	
}