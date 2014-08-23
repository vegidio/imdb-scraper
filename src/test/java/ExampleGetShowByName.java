/**
 * @author Vinícius Egidio (vegidio@gmail.com)
 * Feb 15th 2014
 * v1.0
 */

import com.hryun.imdb.IMDB;

import java.util.List;
import java.util.Map;

public class ExampleGetShowByName
{
	public static void main(String[] args)
	{
		IMDB imdb = new IMDB();
		
		// Search for the show using its name
		List<Map<String, String>> results = imdb.findByName("The Simpsons");
		
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
		imdb.findById(imdbId);
		
		// Check if the show was found
		if(imdb.hasFound())
		{
			// Intentional blank line
			System.out.println("");
			
			System.out.println("Show id....: " + imdb.getId());
			System.out.println("Show url...: " + imdb.getUrl());
			System.out.println("Title/Name.: " + imdb.getTitle());
			System.out.println("Genre......: " + imdb.getGenre());
			System.out.println("Description: " + imdb.getDescription());
			System.out.println("Director...: " + imdb.getDirector());
			System.out.println("Rating.....: " + imdb.getRating());
			System.out.println("Year.......: " + imdb.getYear());
			System.out.println("Recommended: " + imdb.getRecommended());
		}
		else
		{
			System.err.println("Show not found! Wrong id or no internet connection...");
		}
	}
}