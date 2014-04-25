/**
 * @author Vinícius Egidio (vegidio@gmail.com)
 * Feb 15th 2014
 * v1.0
 */

package com.hryun.imdb;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IMDB
{
	// Basic variables
	private String id;
	private String url;
	private boolean found;
	
	// Show information
	private String cast = "";
	private String director = "";
	private String poster = "";
	private float rating = 0f;
	private String recommended = "";
	private String title = "";
	private short year = 0;
	
	public IMDB()
	{
		this.found = false;
	}
	
	/**
	 * Find a show information based on its Id
	 * 
	 * @param id
	 * @return
	 */
	public boolean findById(String id)
	{
		this.id = id;
		this.url = "http://www.imdb.com/title/" + id;
		
		// Get the HTML
		String html = fetchHtml(url);
		
		if(!html.isEmpty())
		{
			found = true;
			parseHtml(html);
		}
		
		return found;
	}
	
	/**
	 * Find a list of shows that match the search parameter
	 * 
	 * @param name
	 * @return
	 */
	public List<Map<String, String>> findByName(String name)
	{
		// Regex
		final String SEARCH_ID_NAME = "\"result_text\"> <a href=\"/title/tt([0-9]*)/(.*?)\" >(.*?)</a>";
		
		// Variables
		Pattern pattern;
		Matcher matcher;
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		Map<String, String> map = null;
		
		// Do the search and get the HTML
		String search = name.replace(" ", "+");
		search = "http://www.imdb.com/find?q=" + search + "&s=all";
		String html = fetchHtml(search);
		
		// Extract the ID & Name
		pattern = Pattern.compile(SEARCH_ID_NAME);
		matcher = pattern.matcher(html);
		
		while(matcher.find())
		{
			map = new LinkedHashMap<String, String>();
			map.put("id", "tt" + matcher.group(1));
			map.put("name", matcher.group(3));
			list.add(map);
		}
		
		return list;
	}
	
	/**
	 * Retrive the HTML content of the page
	 * 
	 * @param urlString
	 * @return
	 */
	private String fetchHtml(String urlString)
	{
		String line;
		StringBuffer html = new StringBuffer();
		
		try
		{
			URL url = new URL(urlString);
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			
			// Fake the User-Agent
			conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_1) AppleWebKit/537.36 " +
					"(KHTML, like Gecko) Chrome/32.0.1700.107 Safari/537.36");
			
			// Check the HTTP response code
			if(conn.getResponseCode() == 200)
			{
				BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
				
				// Reading the HTML
				while((line = in.readLine()) != null)
					html.append(line.trim());
				
				in.close();
			}
			
			// Close the connection
			conn.disconnect();
		}
		catch(MalformedURLException e)
		{
			e.printStackTrace();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
		return html.toString();
	}
	
	/**
	 * Parse the HTML code and puts the extracted information in the corresponding fields
	 * 
	 * @param html
	 */
	private void parseHtml(String html)
	{
		// Regex
		final String IMDB_CAST      = "itemprop=\"actor\"(.*?)<span class=\"itemprop\" itemprop=\"name\">(.*?)</span>";
		final String IMDB_DIRECTOR  = "(Director|Directors):</h4>(.*?)</div>"; 
		final String IMDB_NAME      = "<span class=\"itemprop\" itemprop=\"name\">(.*?)</span>";
		final String IMDB_POSTER    = "<div class=\"image\">(.*?)src=\"(.*?)\"(.*?)itemprop=\"image\" />";
		final String IMDB_RATING    = "<span itemprop=\"ratingValue\">(.*?)</span>";
		final String IMDB_TITLE     = "property='og:title' content=\"(.*?) \\((.*?)([0-9]{4}?)";
		final String IMDB_YEAR      = "property='og:title' content=\"(.*?) \\((.*?)([0-9]{4}?)";
		final String IMDB_RECOMMEND = "<div class=\"rec_item\"(.*?)<a href=\"/title/(.*?)/\\?ref_=tt_rec_tti\" >"; 
		
		// Variables
		Pattern pattern;
		Matcher matcher;
		String tempHtml = "";
		
		// Get the cast
		pattern = Pattern.compile(IMDB_CAST);
		matcher = pattern.matcher(html);
		while(matcher.find()) cast = (cast.length() > 0)? cast + ", " + matcher.group(2): matcher.group(2);
		
		// Get the diretor/name
		pattern = Pattern.compile(IMDB_DIRECTOR);
		matcher = pattern.matcher(html);
		if(matcher.find()) tempHtml = matcher.group(2);
		
		pattern = Pattern.compile(IMDB_NAME);
		matcher = pattern.matcher(tempHtml);
		while(matcher.find()) director = (director.length() > 0)? director + ", " + matcher.group(1): matcher.group(1);
		
		// Get the poster
		pattern = Pattern.compile(IMDB_POSTER);
		matcher = pattern.matcher(html);
		if(matcher.find()) poster = matcher.group(2);
		
		// Get the rating
		pattern = Pattern.compile(IMDB_RATING);
		matcher = pattern.matcher(html);
		if(matcher.find()) rating = Float.parseFloat(matcher.group(1));
		
		// Get the cast
		pattern = Pattern.compile(IMDB_RECOMMEND);
		matcher = pattern.matcher(html);
		while(matcher.find()) recommended = (recommended.length() > 0)? recommended + ", " + matcher.group(2):
			matcher.group(2);
		
		// Get the title
		pattern = Pattern.compile(IMDB_TITLE);
		matcher = pattern.matcher(html);
		if(matcher.find()) title = matcher.group(1);
		
		// Get the year
		pattern = Pattern.compile(IMDB_YEAR);
		matcher = pattern.matcher(html);
		if(matcher.find()) year = Short.parseShort(matcher.group(3));
	}
	
	/**
	 * Saves the poster locally
	 * 
	 * @param posterFile
	 */
	public void downloadPoster(File posterFile)
	{
		if(!this.poster.isEmpty())
		{
			try
			{
				URL posterUrl = new URL(this.poster);
				ReadableByteChannel rbc = Channels.newChannel(posterUrl.openStream());
				FileOutputStream fos = new FileOutputStream(posterFile);
				fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
				fos.close();
			}
			catch(MalformedURLException e)
			{
				e.printStackTrace();
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Returns true if we have found a show doing a search with findById
	 * 
	 * @return
	 */
	public boolean hasFound()
	{
		return found;
	}

	/**
	 * @return the id
	 */
	public String getId()
	{
		return id;
	}

	/**
	 * @return the url
	 */
	public String getUrl()
	{
		return url;
	}

	/**
	 * @return the cast
	 */
	public String getCast()
	{
		return cast;
	}

	/**
	 * @return the director
	 */
	public String getDirector()
	{
		return director;
	}

	/**
	 * @return the rating
	 */
	public float getRating()
	{
		return rating;
	}

	/**
	 * @return the recommended
	 */
	public String getRecommended()
	{
		return recommended;
	}

	/**
	 * @return the title
	 */
	public String getTitle()
	{
		return title;
	}

	/**
	 * @return the year
	 */
	public short getYear()
	{
		return year;
	}
}