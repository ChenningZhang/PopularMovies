package com.chenning.movies.popularmovie.Task;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.chenning.movies.popularmovie.MovieAdapter;
import com.chenning.movies.popularmovie.MovieObject;
import com.chenning.movies.popularmovie.R;
import com.chenning.movies.popularmovie.Utils.JsonUtils;
import com.chenning.movies.popularmovie.Utils.NetworkUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by chenningzhang on 1/24/18.
 */

public class FetchMoviesTask extends AsyncTask<String, Void, ArrayList<MovieObject>> {

    private static final String TAG = FetchMoviesTask.class.getSimpleName();
    private MovieAdapter mMovieAdapter;
    private Context mContext;
    private ProgressBar mProgressBar;

    public FetchMoviesTask(Context context, MovieAdapter movieAdapter) {
        mContext = context;
        mMovieAdapter = movieAdapter;
        mProgressBar = (ProgressBar) ((Activity)mContext).findViewById(R.id.pb_posters_progress_bar);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    protected ArrayList<MovieObject> doInBackground(String... strings) {
        String sortOrder = strings[0];
        URL fetchMoviesUrl = NetworkUtils.buildUrl(sortOrder);

        try {
            HttpsURLConnection httpsURLConnection = (HttpsURLConnection) fetchMoviesUrl.openConnection();
            InputStream inputStream = httpsURLConnection.getInputStream();
            String responseString = null;

            Scanner scanner = new Scanner(inputStream);
            scanner.useDelimiter("\\A");

            if (scanner.hasNext()) {
                responseString = scanner.next();
            }
            Log.d(TAG, "RESPONSE: " + responseString);
            ArrayList<MovieObject> movieObjects = JsonUtils.getMoviesFromResponse(responseString);
            httpsURLConnection.disconnect();
            return movieObjects;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(ArrayList<MovieObject> movieObjects) {
        mProgressBar.setVisibility(View.INVISIBLE);
        ((SwipeRefreshLayout) ((Activity) mContext).findViewById(R.id.srl_movies)).setRefreshing(false);
        if (movieObjects != null) {
            mMovieAdapter.setMovieObjects(movieObjects);
        } else {
            Toast.makeText(mContext, "Uh oh. Something went wrong. Please check your internet connection.", Toast.LENGTH_LONG).show();
        }
    }
}
