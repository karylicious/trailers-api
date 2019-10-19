package com.movies.models;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class YouTubeResultList{		
	
	@JsonProperty("items")
	private List<YouTubeItem> youTubeItemsList;
	
	public List<YouTubeItem> getYouTubeItemsList() {
		return youTubeItemsList;
	}
}