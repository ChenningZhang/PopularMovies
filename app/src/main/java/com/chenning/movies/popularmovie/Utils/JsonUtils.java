package com.chenning.movies.popularmovie.Utils;

import android.util.Log;

import com.chenning.movies.popularmovie.MovieObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by chenningzhang on 1/31/18.
 */

public class JsonUtils {

    private static final String TAG = JsonUtils.class.getSimpleName();
    private static final String TMDB_LIST = "results";
    private static final String TMDB_ID = "id";
    private static final String TMDB_TITLE = "title";
    private static final String TMDB_POSTER_PATH = "poster_path";
    private static final String TMDB_OVERVIEW = "overview";
    private static final String TMDB_RELEASE_DATE = "release_date";
    private static final String TMDB_RATING = "vote_average";

    public static ArrayList<String> getPosterPathsFromResponse(String responseString) {
        ArrayList<String> result = new ArrayList<>();
        JSONObject responseObject;
        JSONArray moviesArray;
        try {
            responseObject = new JSONObject(responseString);
            moviesArray = responseObject.getJSONArray(TMDB_LIST);
            for (int i = 0; i < moviesArray.length(); i++) {
                JSONObject movieObject = moviesArray.getJSONObject(i);
                result.add(movieObject.get(TMDB_POSTER_PATH).toString());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d(TAG, result.toString());
        return result;
    }

    public static ArrayList<MovieObject> getMoviesFromResponse(String responseString) {
        ArrayList<MovieObject> result = new ArrayList<>();
        JSONObject responseObject;
        JSONArray moviesArray;
        try {
            responseObject = new JSONObject(responseString);
            moviesArray = responseObject.getJSONArray(TMDB_LIST);
            for (int i = 0; i < moviesArray.length(); i++) {
                JSONObject movieObject = moviesArray.getJSONObject(i);

                int movieId = movieObject.getInt(TMDB_ID);
                String movieTitle = movieObject.getString(TMDB_TITLE);
                String moviePosterPath = movieObject.getString(TMDB_POSTER_PATH);
                String movieOverview = movieObject.getString(TMDB_OVERVIEW);
                String movieReleaseDate = movieObject.getString(TMDB_RELEASE_DATE);
                float movieRating = movieObject.getLong(TMDB_RATING);

                MovieObject object = new MovieObject(movieId, movieTitle, movieRating, moviePosterPath, movieOverview, movieReleaseDate);
                result.add(object);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d(TAG, result.toString());
        return result;
    }
}
