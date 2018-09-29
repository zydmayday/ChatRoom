package com.summer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/*
 * @SpringBootApplication equals to the 3 annotations below
 * @EnableAutoConfiguration
 * @ComponentScan
 * @Configuration
 */
@SpringBootApplication(scanBasePackages="com.summer")
public class Application {
	
	@Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("http://localhost:3000");
            }
        };
    }
	
    public static void main(String[] args) {
    	/*
    	 * SpringApplication will create an appropriate ApplicationContext instance
    	 * and refresh the application context, loading all singleton beans.
    	 * ApplicationContext will register @Configuration in other package but I don't know how.
    	 */
        SpringApplication.run(Application.class, args);
    }
}