/**
 * @author Vinícius Egidio (vegidio@gmail.com)
 * Feb 15th 2014
 * v1.0
 */

package com.hryun.imdb;

public class ExampleGetShowById
{
	public static void main(String[] args)
	{
		Scraper scraper = new Scraper();
		
		// Get a show by its IMDb id; in this case, The Simpsons
		scraper.findById("tt0096697");
		
		// Check if the show was found
		if(scraper.hasFound())
		{
			System.out.println("Show id....: " + scraper.getId());
			System.out.println("Show url...: " + scraper.getUrl());
			System.out.println("Title/Name.: " + scraper.getTitle());
			System.out.println("Genre......: " + scraper.getGenre());
			System.out.println("Description: " + scraper.getDescription());
			System.out.println("Director...: " + scraper.getDirector());
			System.out.println("Rating.....: " + scraper.getRating());
			System.out.println("Year.......: " + scraper.getYear());
			System.out.println("Recommended: " + scraper.getRecommended());
			System.out.println("Videos.....: " + scraper.getVideos());
		}
		else
		{
			System.err.println("Show not found! Wrong id or no internet connection...");
		}
	}
}