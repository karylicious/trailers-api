package com.movies.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
//import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.movies.models.Genre;
import com.movies.services.TmdbService;

// CORS access control headers (without this the client won't be able to access the response from this web service)
@CrossOrigin(origins = "*") 
@RestController
public class GenreController {
	
	@Autowired
	private TmdbService tmdbClient;
	
	@RequestMapping(path = "/api/v1/genres", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Genre> getGenres() {
		return tmdbClient.getGenres();
	}	
}