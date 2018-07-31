package com.example.rajagopalan.movieapplication;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Rajagopalan on 3/23/2017.
 * Class contains properties and methods for a movie object
 */
public class Movie implements Parcelable {

    private double popularity;
    private String title;
    private String overview;
    private String backdrop_path;
    private String imageUrl;
    private String original_language;
    private String release_date;

    public String getOriginal_language() {
        return original_language;
    }

    public String getRelease_date() {
        return release_date;
    }

    public String getOverview() {
        return overview;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public Double getPopularity() {
        return popularity;
    }

    public String getTitle() {
        return title;
    }

    public String getImageUrl() {
        final String IMAGE_SIZE = "w500";
        imageUrl = "https://image.tmdb.org/t/p/" + IMAGE_SIZE + backdrop_path;
        return imageUrl;
    }

    /**
     * This method describes contents. No functionality here.
     * @return
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * Transfers instance variables to destination parcel
     * @param destinationParcel The parcel you want to write to
     * @param flags integer parameter needed to coreectly override the method.
     */
    @Override
    public void writeToParcel(Parcel destinationParcel, int flags) {
        destinationParcel.writeString(this.title);
        destinationParcel.writeString(this.overview);
        destinationParcel.writeString(this.backdrop_path);
        destinationParcel.writeString(this.original_language);
        destinationParcel.writeString(this.release_date);
        }

    /**
     * Movie default constructor
     */
    public Movie() {
        this.title = null;
        this.overview = null;
        this.backdrop_path = null;
        this.original_language = null;
        this.release_date = null;
    }

    /**
     * Movie parameterized constructor
     *
     * @param in The parcel conatining values for the instance variables
     */
    protected Movie(Parcel in) {
        this.title = in.readString();
        this.overview = in.readString();
        this.backdrop_path = in.readString();
        this.original_language = in.readString();
        this.release_date = in.readString();
        }

    /**
     * Auto generated code needed for parcelable implementation
     */
    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel source) {
            return new Movie(source);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };
}