package com.example.rajagopalan.movieapplication;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;

/**
 * Class that fills recycler view
 */
public class FillRecyclerView extends RecyclerView.Adapter<FillRecyclerView.ViewHolder> {

    public static final String MOVIE = "MOVIE";
    private ArrayList<Movie> movieList;

    public FillRecyclerView(ArrayList<Movie> movies) {
        this.movieList = movies;
    }

    /**
     * Method fills screen with views. Recycles view when scrolling.
     *
     * @param parentViewGroup parent ViewGroup object
     * @param type            int parameter needed to override the superclass method.
     *                        No functionality here
     * @return A new ViewHolder
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parentViewGroup, int type) {
        final View movieListView = LayoutInflater.from(parentViewGroup.getContext()).
                inflate(R.layout.movie, parentViewGroup, false);
        return new ViewHolder(movieListView);
    }

    /**
     * Method fills title, popularity and image into the required position in the list
     *
     * @param viewHolder the ViewHolder containing the Views we need to fill
     * @param index      the index into the arrayList of Movies
     */
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int index) {
        final Movie movie = movieList.get(index);
        viewHolder.movieTitle.setText("Title: " + movie.getTitle());
        viewHolder.popularity.setText("Popularity: " + movie.getPopularity());
        Picasso.with(viewHolder.image.getContext())
                .load(movie.getImageUrl()).into(viewHolder.image);

        // This OnclickListener allows us to click on a movie to see additional info
        viewHolder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DetailsActivity.class);
                intent.putExtra(MOVIE, movie);
                v.getContext().startActivity(intent);
            }
        });
    }

    /**
     * Returns number of movies in the arrayList
     *
     * @return the number of movies in the arrayList
     */
    @Override
    public int getItemCount() {
        return movieList.size();
    }

    /**
     * Caching references to subviews
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public View view;
        public TextView movieTitle;
        public TextView popularity;
        public ImageView image;
        public TextView LatitudeText;


        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            movieTitle = (TextView) itemView.findViewById(R.id.titleTextView);
            popularity = (TextView) itemView.findViewById(R.id.popularityTextView);
            image = (ImageView) itemView.findViewById(R.id.imageView);
        }
    }
}
