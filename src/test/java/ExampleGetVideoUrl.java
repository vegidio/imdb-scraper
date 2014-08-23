/**
 * @author Vinícius Egidio (vegidio@gmail.com)
 * Feb 15th 2014
 * v1.0
 */

import com.hryun.imdb.IMDB;

public class ExampleGetVideoUrl
{
	public static void main(String[] args)
	{
		IMDB imdb = new IMDB();
		
		// Get a show by its IMDb id; in this case, The Simpsons
		String url = imdb.getVideoUrl("vi972070937");
		System.out.println(url);
		System.out.println("Done!");
	}
}