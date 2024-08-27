package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();

        // Change localhost:3000 to your Heroku frontend URL
        config.addAllowedOrigin("https://photo-frontend-1c10f276a77f.herokuapp.com"); // Allows requests from your React
                                                                                      // app
        config.addAllowedHeader("*"); // Allows all headers
        config.addAllowedMethod("*"); // Allows all HTTP methods (GET, POST, etc.)
        config.setAllowCredentials(true); // Allows credentials like cookies to be included

        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
