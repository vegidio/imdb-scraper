/**
 * @author Vinícius Egidio (vegidio@gmail.com)
 * Feb 15th 2014
 * v1.0
 */

import com.hryun.imdb.IMDB;

public class ExampleGetShowById
{
	public static void main(String[] args)
	{
		IMDB imdb = new IMDB();
		
		// Get a show by its IMDb id; in this case, The Simpsons
		imdb.findById("tt0944947");
		
		// Check if the show was found
		if(imdb.hasFound())
		{
			System.out.println("Show id....: " + imdb.getId());
			System.out.println("Show url...: " + imdb.getUrl());
			System.out.println("Title/Name.: " + imdb.getTitle());
			System.out.println("Genre......: " + imdb.getGenre());
			System.out.println("Description: " + imdb.getDescription());
			System.out.println("Director...: " + imdb.getDirector());
			System.out.println("Rating.....: " + imdb.getRating());
			System.out.println("Year.......: " + imdb.getYear());
			System.out.println("Recommended: " + imdb.getRecommended());
			System.out.println("Videos.....: " + imdb.getVideos());
		}
		else
		{
			System.err.println("Show not found! Wrong id or no internet connection...");
		}
	}
}