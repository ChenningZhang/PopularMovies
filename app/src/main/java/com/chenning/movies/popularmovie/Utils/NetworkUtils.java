package com.chenning.movies.popularmovie.Utils;

import android.net.Uri;
import android.util.Log;

import com.chenning.movies.popularmovie.BuildConfig;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by chenningzhang on 1/24/18.
 */

public class NetworkUtils {
    private static final String TAG = NetworkUtils.class.getSimpleName();

    private static final String BASE_URL = "https://api.themoviedb.org";
    private static final String IMAGE_BASE_URL = "https://image.tmdb.org/t/p";
    private static final String DEFAULT_IMAGE_SIZE = "w185";
    private static final String API_VERSION = "3";
    private static final String MOVIE_PATH = "movie";
    private static final String API_PARAM = "api_key";

    public static URL buildUrl(String sortOrder) {
        Uri builtUri = Uri.parse(BASE_URL).buildUpon()
                .appendPath(API_VERSION)
                .appendPath(MOVIE_PATH)
                .appendPath(sortOrder)
                .appendQueryParameter(API_PARAM, BuildConfig.API_KEY)
                .build();
        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        Log.d(TAG, url.toString());
        return url;
    }

    public static URL buildFetchPosterUrl(String posterPath, String imageSize) {
        Uri builtUri = Uri.parse(IMAGE_BASE_URL).buildUpon()
                .appendPath(imageSize)
                .appendEncodedPath(posterPath)
                .build();
        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        Log.d(TAG, "Image URL: " + url);
        return url;
    }

    public static URL buildFetchPosterUrlWithDefaultSize(String posterPath) {
        return buildFetchPosterUrl(posterPath, DEFAULT_IMAGE_SIZE);
    }
}
