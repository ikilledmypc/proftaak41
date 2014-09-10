package demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
@Configuration
@ComponentScan
@EnableWebMvc
@EnableAutoConfiguration
public class Application extends WebMvcConfigurerAdapter {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		System.out.println("configuration updated");
        // I tried these many combinations separately.
		  registry.addResourceHandler("/assets/**")
		    .addResourceLocations("classpath:/assets/");
		
             // do the classpath works with the directory under webapp?
		}
    

}
