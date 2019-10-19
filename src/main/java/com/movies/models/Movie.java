package com.movies.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Movie {	
	
	@JsonProperty("id")
	private int id;

	private String title;
	
	@JsonProperty("poster_path")
	private String posterPath;

	@JsonProperty("release_date")
	private String releaseDate;
	
	public String getTitle() {
		return title;
	}
}