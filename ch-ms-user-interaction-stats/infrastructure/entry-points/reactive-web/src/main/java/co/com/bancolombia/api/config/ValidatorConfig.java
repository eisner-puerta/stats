package co.com.bancolombia.api.config;

import co.com.bancolombia.model.validators.HashValidator;
import co.com.bancolombia.model.validators.IHashValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ValidatorConfig {

    @Bean
    public IHashValidator hashValidator() {
        return new HashValidator();
    }
}