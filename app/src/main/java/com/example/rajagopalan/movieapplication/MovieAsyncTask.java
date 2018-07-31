package com.example.rajagopalan.movieapplication;
import android.content.Context;
import android.os.AsyncTask;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

/**
 * This class is a subclass of abstract class AsyncTask and implements the necessary methods
 */
public class MovieAsyncTask extends AsyncTask<URL, Void, Movie[]> {

    private Context context;
    private ArrayList<Movie> movieList = new ArrayList<>();
    private FillRecyclerView movieView;

    /**
     * Parameterized constructor
     * @param context The context you want to give the object
     */
    public MovieAsyncTask(Context context, ArrayList movieList, FillRecyclerView movieView) {
        this.context = context;
        this.movieList = movieList;
        this.movieView = movieView;
    }

    /**
     * Parses URL on a background thread
     * @param urlList Array of URL objects
     * @return Array of all movies on the first page
     */
    @Override
    protected Movie[] doInBackground(URL... urlList) {
        Movie[] movies = null;
        try {
            movies = ParseMovie.parseUrl(urlList);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return movies;
    }

    /**
     * Method is called after background task is executed
     * @param movies Array of all movies on the first page
     */
    @Override
    protected void onPostExecute(Movie[] movies) {
        movieList.clear();
        for (int i = 0; i < movies.length; i++) {
            movieList.add(movies[i]);
        }
        movieView.notifyDataSetChanged();
    }
}