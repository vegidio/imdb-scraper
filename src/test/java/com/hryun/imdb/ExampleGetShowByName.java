/**
 * @author Vinícius Egidio (vegidio@gmail.com)
 * Feb 15th 2014
 * v1.0
 */

package com.hryun.imdb;

import java.util.List;
import java.util.Map;

public class ExampleGetShowByName
{
	public static void main(String[] args)
	{
		Scraper scraper = new Scraper();
		
		// Search for the show using its name
		List<Map<String, String>> results = scraper.search("The Simpsons", SearchType.TITLE);
		
		/*
		 * We _usually_ (not always!) find more than one result when we search by the name because:
		 * (1) There can be more than one show with the same name
		 * (2) IMDb returns results with names _similar_ of what you searched; there's no way to disable that
		 */
		for(int i = 0; i < results.size(); i++)
		{
			// Let's print all shows found
			System.out.println("Show #" + String.valueOf(i+1) + " ----------------------------------------");
			System.out.println("Show id....: " + results.get(i).get("id"));
			System.out.println("Name.......: " + results.get(i).get("name"));
		}
		
		/*
		 * The first show found in the result list is _usually_ (again, not always!) the show that you're looking for.
		 * In this example, The Simpsons (the animated series), is the first one in the list (at least it was when I
		 * wrote this example). Lets get the show information using its id and print on the screen.
		 */
		String imdbId = results.get(0).get("id");
		scraper.findById(imdbId);
		
		// Check if the show was found
		if(scraper.hasFound())
		{
			// Intentional blank line
			System.out.println("");
			
			System.out.println("Show id....: " + scraper.getId());
			System.out.println("Show url...: " + scraper.getUrl());
			System.out.println("Title/Name.: " + scraper.getTitle());
			System.out.println("Genre......: " + scraper.getGenre());
			System.out.println("Description: " + scraper.getDescription());
			System.out.println("Director...: " + scraper.getDirector());
			System.out.println("Rating.....: " + scraper.getRating());
			System.out.println("Year.......: " + scraper.getYear());
			System.out.println("Recommended: " + scraper.getRecommended());
		}
		else
		{
			System.err.println("Show not found! Wrong id or no internet connection...");
		}
	}
}