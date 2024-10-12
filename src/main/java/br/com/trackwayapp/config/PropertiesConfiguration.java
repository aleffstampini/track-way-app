package br.com.trackwayapp.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Primary
@Configuration
@Data
public class PropertiesConfiguration {

    @Value("${zip_code.api.base_url}")
    private String zipCodeApiBaseUrl;

}