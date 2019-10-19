package com.movies.services;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.movies.models.YouTubeItem;
import com.movies.models.YouTubeResultList;

/* This class is used as a client stub which will consume YouTube Data API 
 * 
 * According to the YouTube Data API reference  every request must either 
 * specify an API key  (with the key parameter) or provide an OAuth 2.0 token. 
 * 
 * This class uses the API key, which was generated at the  API library.
 * 
 * For more information: https://developers.google.com/youtube/v3/docs
 * 
 */
@Service
public class YouTubeService {

	private final static String YOUTUBE_API_URL = "https://www.googleapis.com/youtube/v3/search";
	private final static String YOUTUBE_DATA_API_KEY = "";
		
	@Autowired
	private RestTemplate restTemplate;
		
	public String getMovieTrailerIDFromYouTubeAPI(String movieTitle) {		
		
		String requestURL = getFormattedURL(movieTitle);	
		
		// The RestTemplate retrieves the resource by sending a HTTP GET request and unmarshals it into the class YouTubeResult		
		YouTubeResultList youTubeResult = restTemplate.getForObject(requestURL, YouTubeResultList.class);			
		
		return getVideoIdFromYouTubeResult(youTubeResult);
	}
	
	private String getFormattedURL(String queryTerm) {
		
		String formattedQueryTerm = queryTerm.replace(' ','+');
		formattedQueryTerm += "+trailer"; 		
		int maxResults = 1;
		
		return new StringBuilder(YOUTUBE_API_URL)
				.append("?q=")
				.append(formattedQueryTerm)
				.append("&part=snippet&type=video&videoEmbeddable=true&maxResults=")
				.append(maxResults)
				.append("&key=")
				.append(YOUTUBE_DATA_API_KEY)
				.toString();
	}	
	
	private String getVideoIdFromYouTubeResult(YouTubeResultList youTubeResult) {
		
		YouTubeItem firstItem = youTubeResult.getYouTubeItemsList().get(0);
		Map<String, String> youtubeItemId = firstItem.getItemId();
		
		// The key "videoId" comes from a property name in JSON response from YouTube API
		return youtubeItemId.get("videoId");
	}
}