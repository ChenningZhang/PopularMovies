package com.chenning.movies.popularmovie;

import java.io.Serializable;

/**
 * Created by chenningzhang on 2/6/18.
 */

public class MovieObject implements Serializable {

    private final int id;
    private final String title;
    private final float rating;
    private final String posterPath;
    private final String overview;
    private final String releaseDate;

    public MovieObject(int id, String title, float rating, String posterPath, String overview, String releaseDate) {
        this.id = id;
        this.title = title;
        this.rating = rating;
        this.posterPath = posterPath;
        this.overview = overview;
        this.releaseDate = releaseDate;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public float getRating() {
        return rating;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public String getOverview() {
        return overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }
}
