package Main;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.MultipartConfigElement;

import managers.SettingsManager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.embedded.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import domain.Account;

@Configuration
@ComponentScan("controllers")
@EnableAutoConfiguration
@EnableWebMvc
@PropertySource("application.properties")
public class Application {

    public static void main(String[] args) {
    	
    	SettingsManager settings = new SettingsManager();
    	settings.loadOrCreate();
        SpringApplication.run(Application.class, args);
    }
    
    @Bean
    MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize("50MB");
        factory.setMaxRequestSize("50MB");
        return factory.createMultipartConfig();
    }
}
