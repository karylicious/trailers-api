package com.movies.services;

import org.springframework.web.client.RestTemplate;

import com.movies.models.Genre;
import com.movies.models.MovieFullVersion;
import com.movies.models.OptionalSearchFilter;
import com.movies.models.TmdbGenreList;
import com.movies.models.TmdbPageMovieList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/* This class is used as a client stub which will consume TMDB API 
 * 
 * To access the Rotten tomatoes data I have to ask for permission 
 * by submitting a proposal form describing how I would use the data, therefore 
 * I decided to go for IMDB API.
 * 
 * However the IMDB has no public API. As an alternative I am using TMDB API that 
 * provides the exact functionality of IMDB.
 * 
 * According to the TMDB API reference every request must specify an API key. 
 * 
 * The API key used by this class was generated after creating an account. 
 * 
 * For more information: https://developers.themoviedb.org/3/getting-started/introduction
 * 
 */

@Service
public class TmdbService {

	private final static String TMDB_API_URL = "https://api.themoviedb.org/3";
	private final static String TMDB_API_KEY = "";	
	
	@Autowired
	private RestTemplate restTemplate;	
	
	public TmdbPageMovieList getMoviesByTitle(String movieTitle) {
		String requestURL = getFormattedURLForSimpleSearch(movieTitle);				
		return  restTemplate.getForObject(requestURL, TmdbPageMovieList.class);
	}
	
	public TmdbPageMovieList getMoviesByOptionalFilters(OptionalSearchFilter optionalSearchFilters) {		
		String concatenatedOptionalFilters = getConcatenatedNonEmptyFilter(optionalSearchFilters);
		
		if(concatenatedOptionalFilters.equals(""))
			return new TmdbPageMovieList();
		
		String requestURL = getFormattedURLForAdvancedSearch(concatenatedOptionalFilters);	
		return  restTemplate.getForObject(requestURL, TmdbPageMovieList.class);		
	}
	
	public List<Genre> getGenres() {		
		String requestURL = getFormattedURLForGenres();				
		TmdbGenreList listOfGenres = restTemplate.getForObject(requestURL, TmdbGenreList.class);	
		return listOfGenres.getListOfGenres();
	}
	
	public MovieFullVersion getMovieById(int movieId) {
		String requestURL = getFormattedURLForMovieDetails(movieId);			
		return restTemplate.getForObject(requestURL, MovieFullVersion.class);	
	}
	
	private String getConcatenatedNonEmptyFilter(OptionalSearchFilter optionalSearchFilters) {
		StringBuilder concatenatedFilters = new StringBuilder("");
		
		String genreIds = optionalSearchFilters.getGenreIds();		
		if (genreIds != null) 
			concatenatedFilters = appendSearchFiltersToStringBuilder(concatenatedFilters, "with_genres=", genreIds);
						  
		String releaseYear = optionalSearchFilters.getReleaseYear();
		if(releaseYear != null)
			concatenatedFilters = appendSearchFiltersToStringBuilder(concatenatedFilters, "primary_release_year=", releaseYear);
		
		String rateGreaterOrEqual = optionalSearchFilters.getRateGreaterOrEqual();		
		if(rateGreaterOrEqual != null)
			concatenatedFilters = appendSearchFiltersToStringBuilder(concatenatedFilters, "vote_average.gte=", rateGreaterOrEqual);
							
		String rateLessOrEqual = optionalSearchFilters.getRateLessOrEqual();		
		if(rateGreaterOrEqual != null)
			concatenatedFilters = appendSearchFiltersToStringBuilder(concatenatedFilters, "vote_average.lte=", rateLessOrEqual);
		
		return concatenatedFilters.toString();
	}
	
	private StringBuilder appendSearchFiltersToStringBuilder(StringBuilder concatenatedFilters, String filterNameProceededByEqualSign, String filterValue) {
		String ampersand = (concatenatedFilters.length() == 0) ? "" : "&";	
		
		return concatenatedFilters.append(ampersand)	
								  .append(filterNameProceededByEqualSign)
								  .append(filterValue);		
	}
	
	private String getFormattedURLForSimpleSearch(String queryTerm) {		
		String formattedQueryTerm = queryTerm.replace(' ','+'); 
		
		return new StringBuilder(TMDB_API_URL)
				.append("/search/movie?query=")
				.append(formattedQueryTerm)
				.append("&api_key=")
				.append(TMDB_API_KEY)
				.toString();
	}	
	
	private String getFormattedURLForAdvancedSearch(String queryTerm) {	
		return new StringBuilder(TMDB_API_URL)
				.append("/discover/movie?")
				.append(queryTerm)
				.append("&api_key=")
				.append(TMDB_API_KEY)
				.toString();
	}	
	
	private String getFormattedURLForGenres() {			
		return new StringBuilder(TMDB_API_URL)
				.append("/genre/movie/list?api_key=")
				.append(TMDB_API_KEY)
				.toString();
	}
	
	private String getFormattedURLForMovieDetails(int id) {		
		return new StringBuilder(TMDB_API_URL)
				.append("/movie/")
				.append(id)
				.append("?api_key=")
				.append(TMDB_API_KEY)
				.toString();
	}	
}