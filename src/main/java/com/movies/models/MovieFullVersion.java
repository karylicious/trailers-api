package com.movies.models;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class MovieFullVersion extends Movie{
		
	@JsonProperty("overview")
	private String overview;
	
	@JsonProperty("vote_average")
	private double rate;	
	
	@JsonProperty("genres")
	private List<Genre> genres;
		
	@JsonProperty("trailer_id")
	private String trailerId;
		
	public void setTrailerId(String trailerId) {
		this.trailerId = trailerId;
	}	
}