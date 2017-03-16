# IMDb Scraper

[![JitPack](https://jitpack.io/v/vegidio/imdb-scraper.svg)](https://jitpack.io/#vegidio/imdb-scraper)
[![Apache 2.0](https://img.shields.io/badge/license-Apache_License_2.0-blue.svg)](http://www.apache.org/licenses/LICENSE-2.0)

This library lets you get IMDb information from movies, series, etc without an API key.

## Installation

### Gradle

Add in your file `build.gradle` at the end of repositories:

```
allprojects {
	repositories {
		// ...
		maven { url "https://jitpack.io" }
	}
}
```

Then add the dependency:

```
dependencies {
	compile 'com.github.vegidio:imdb-scraper:1.0.2'
}
```

### Maven

Add in your file `pom.xml` at the end of repositories:

```xml
<repositories>
	<repository>
		<id>jitpack.io</id>
		<url>https://jitpack.io</url>
	</repository>
</repositories>
```

Then add the dependency:

```xml
<dependency>
	<groupId>com.github.vegidio</groupId>
	<artifactId>imdb-scraper</artifactId>
	<version>1.0.2</version>
</dependency>
```

## Usage

```java
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
	// ...
}
```

More examples can be found in the folder `src/test/java/com/hryun/imdb/`.

## License

**imdb-scraper** is released under the Apache License. See <a href="LICENSE.txt">LICENSE</a> for details.

## Author

Vinicius Egidio (<a href="http://vinicius.io">vinicius.io</a>)
