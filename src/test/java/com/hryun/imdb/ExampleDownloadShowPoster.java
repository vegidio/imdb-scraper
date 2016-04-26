/**
 * @author Vinícius Egidio (vegidio@gmail.com)
 * Feb 15th 2014
 * v1.0
 */

package com.hryun.imdb;

import java.io.File;

public class ExampleDownloadShowPoster
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

            /*
             * Save the poster locally, in the folder '/Users/vegidio/poster.jpg'
             * Check if the folder exists! The folder structure will not be created if it doesn't exist, only the poster
             * file will created.
             */
            File posterFile = new File("/Users/vegidio/poster.jpg");
            File bigPosterFile = new File("/Users/vegidio/poster_big.jpg");

            // Download the show's poster. If the show doesn't have a poster, the following function does nothing
            scraper.downloadPoster(posterFile);
            scraper.downloadPoster(bigPosterFile, true);
        }
        else
        {
            System.err.println("Show not found! Wrong id or no internet connection...");
        }
    }
}