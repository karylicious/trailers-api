package com.movies.models;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TmdbGenreList{		
	
	@JsonProperty("genres")
	private List<Genre> listOfGenres;
	
	public List<Genre> getListOfGenres() {
		return listOfGenres;
	}
}