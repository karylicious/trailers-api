package com.movies;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

import com.movies.models.OptionalSearchFilter;
import com.movies.services.TmdbService;
import com.movies.services.YouTubeService;

@SpringBootApplication
@ComponentScan ({"com.movies.controllers", "com.movies.models", "com.movies.services"})
public class MoviesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MoviesApplication.class, args);
	}
	
	@Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
	
	@Bean
	public YouTubeService getYouTubeClient() {
		return new YouTubeService();
	}
	
	@Bean
	public TmdbService getTmdbClient() {
		return new TmdbService();
	}	
	
	@Bean
	public OptionalSearchFilter getOptionalSearchFilter() {
		return new OptionalSearchFilter();
	}
}