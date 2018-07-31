package com.example.rajagopalan.movieapplication;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
/**
 * Created by Rajagopalan on 3/24/2017.
 * Class contains methods that parses JSON data from given URL
 */
public class ParseMovie {

    /**
     * Creates a URL object from the given URL string
     *
     * @return URL object which links to movie data from the first page of the moviedb API
     * @throws MalformedURLException
     */
    public static URL getJsonUrl() throws MalformedURLException {
        final int PAGE_NUM = 1;
        URL JSON_URL = new URL("https://api.themoviedb.org/3/movie/popular?api_key=" +
                ApiKey.API_KEY + "&language=en-US&page=" + PAGE_NUM);
        return JSON_URL;
    }

    /**
     * Parses JSON data from the given URL using the GSON library
     *
     * @param urlList Array of URL objects
     * @return An array of movies from the first page of the URL
     * @throws IOException
     */
    public static Movie[] parseUrl(URL[] urlList) throws IOException {
        URL url = urlList[0];
        InputStream inputStream = url.openStream();
        InputStreamReader inStreamReader = new InputStreamReader(inputStream,
                Charset.forName("UTF-8"));
        Gson gson = new Gson();
        MovieList movieList = gson.fromJson(inStreamReader, MovieList.class);
        return movieList.getResults();
    }
}
