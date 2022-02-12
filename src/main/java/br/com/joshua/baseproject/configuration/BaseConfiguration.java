package br.com.joshua.baseproject.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BaseConfiguration {

	@Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
	
}
