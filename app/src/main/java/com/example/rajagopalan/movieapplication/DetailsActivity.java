package com.example.rajagopalan.movieapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * The activity which displays the description and additional info for each movie
 */
public class DetailsActivity extends AppCompatActivity {

    private TextView titleTextView;
    private TextView languageTextView;
    private TextView overviewTextView;
    private TextView releaseDateTextView;
    private ImageView imageView;

    /**
     * On Create method
     * @param savedInstanceState Saved instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        titleTextView = (TextView) findViewById(R.id.titleTextView);
        overviewTextView = (TextView) findViewById(R.id.overviewTextView);
        languageTextView = (TextView) findViewById(R.id.languageTextView);
        releaseDateTextView = (TextView) findViewById(R.id.releaseDateTextView);
        imageView = (ImageView) findViewById(R.id.imageView);

        Intent intent = getIntent();
        Movie movie = intent.getParcelableExtra(FillRecyclerView.MOVIE);

        titleTextView.setText(movie.getTitle());
        overviewTextView.setText("\nSYNOPSIS" + "\n\n" + movie.getOverview());
        languageTextView.setText("LANGUAGE: " + movie.getOriginal_language());
        releaseDateTextView.setText("RELEASE: " + movie.getRelease_date());
        Picasso.with(imageView.getContext()).load(movie.getImageUrl()).into(imageView);
    }
}
