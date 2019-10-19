package com.movies.models;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TmdbPageMovieList{		
	
	@JsonProperty("page")
	private int currentPage;
	
	@JsonProperty("total_results")
	private int totalResults;
	
	@JsonProperty("total_pages")
	private int totalPages;
	
	@JsonProperty("results")
	private List<Movie> listOfMovies;	
}