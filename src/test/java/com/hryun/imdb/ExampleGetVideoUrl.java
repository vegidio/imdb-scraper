/**
 * @author Vinícius Egidio (vegidio@gmail.com)
 * Feb 15th 2014
 * v1.0
 */

package com.hryun.imdb;

public class ExampleGetVideoUrl
{
	public static void main(String[] args)
	{
		Scraper scraper = new Scraper();
		
		// Get a video by it's IMDb id; in this case, Game of Thrones
		String url = scraper.getVideoUrl("vi972070937");
		System.out.println(url);
	}
}