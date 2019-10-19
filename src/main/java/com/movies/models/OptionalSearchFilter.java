package com.movies.models;

public class OptionalSearchFilter {
	
	private String releaseYear;
	private String genreIds;
	private String rateGreaterOrEqual;
	private String rateLessOrEqual;	
	
	public String getReleaseYear() {
		return releaseYear;
	}
	public void setReleaseYear(String releaseYear) {
		this.releaseYear = releaseYear;
	}
	public String getGenreIds() {
		return genreIds;
	}
	public void setGenreIds(String genreIds) {
		this.genreIds = genreIds;
	}
	public String getRateGreaterOrEqual() {
		return rateGreaterOrEqual;
	}
	public void setRateGreaterOrEqual(String rateGreaterOrEqual) {
		this.rateGreaterOrEqual = rateGreaterOrEqual;
	}
	public String getRateLessOrEqual() {
		return rateLessOrEqual;
	}
	public void setRateLessOrEqual(String rateLessOrEqual) {
		this.rateLessOrEqual = rateLessOrEqual;
	}
}