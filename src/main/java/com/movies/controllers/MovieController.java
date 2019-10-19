package com.movies.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.movies.models.MovieFullVersion;
import com.movies.models.OptionalSearchFilter;
import com.movies.models.TmdbPageMovieList;
import com.movies.services.TmdbService;
import com.movies.services.YouTubeService;

// CORS access control headers (without this the client won't be able to access the response from this web service)
@CrossOrigin(origins = "*") 
@RestController
public class MovieController {
		
	@Autowired
	private TmdbService tmdbClient;
	
	@Autowired
	private YouTubeService youTubeClient;
	
	@Autowired
	OptionalSearchFilter optionalSearchFilters;
	
	@RequestMapping( path = "/api/v1/movies", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public TmdbPageMovieList getMoviesByTitle  ( @RequestParam String title){ 
		return tmdbClient.getMoviesByTitle(title);	
	}
	
	@RequestMapping(path = "/api/v1/movies/search", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public TmdbPageMovieList getMoviesByOptionalFilters(@RequestParam(value = "primary_release_year", required=false) String releaseYear,
									@RequestParam(value = "with_genres", required=false) String genreIds,
									@RequestParam(value = "vote_average.gte", required=false) String rateGreaterOrEqual,
									@RequestParam(value = "vote_average.lte", required=false) String rateLessOrEqual) {
		
		optionalSearchFilters.setGenreIds(genreIds);
		optionalSearchFilters.setRateGreaterOrEqual(rateGreaterOrEqual);
		optionalSearchFilters.setRateLessOrEqual(rateLessOrEqual);
		optionalSearchFilters.setReleaseYear(releaseYear);
		
		return tmdbClient.getMoviesByOptionalFilters(optionalSearchFilters);		
	}
	
	@RequestMapping(value = "/api/v1/movies/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public MovieFullVersion getMovieById(@PathVariable int  id){		
		MovieFullVersion movie = tmdbClient.getMovieById(id);
		String trailerId = youTubeClient.getMovieTrailerIDFromYouTubeAPI(movie.getTitle());
		
		movie.setTrailerId(trailerId); // trailerId of movie object is originally null
		return movie;
	}}