package com.chenning.movies.popularmovie.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.chenning.movies.popularmovie.MovieAdapter;
import com.chenning.movies.popularmovie.MovieAdapterOnClickListner;
import com.chenning.movies.popularmovie.MovieObject;
import com.chenning.movies.popularmovie.R;
import com.chenning.movies.popularmovie.Task.FetchMoviesTask;

public class MainActivity extends AppCompatActivity implements MovieAdapterOnClickListner {
    private static final String TAG = MainActivity.class.getSimpleName();
    public static final String DETAIL_INTENT_TAG = "detail.intent.movie.object";
    private RecyclerView mRecyclerView;
    private MovieAdapter mMovieAdapter;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_movies);
        mMovieAdapter = new MovieAdapter(this);
        mRecyclerView.setAdapter(mMovieAdapter);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 3);
        mRecyclerView.setLayoutManager(layoutManager);

        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.srl_movies);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadMovies();
            }
        });

        loadMovies();
    }

    @Override
    protected void onStart() {
        super.onStart();
        loadMovies();
    }

    @Override
    public void onClick(MovieObject movieObject) {
        Intent detailActivityIntent = new Intent(this, DetailActivity.class);
        detailActivityIntent.putExtra(DETAIL_INTENT_TAG, movieObject);
        startActivity(detailActivityIntent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.options, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_sort) {
            Intent intentSettings = new Intent(this, SettingsActivity.class);
            startActivity(intentSettings);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void loadMovies() {
        SharedPreferences mSharedPreference = PreferenceManager.getDefaultSharedPreferences(this);
        String sortOrderPref = mSharedPreference.getString(getString(R.string.pref_sort_order_key), getString(R.string.pref_sort_order_popular_value));
        Log.d(TAG, "Movies sort by: " + sortOrderPref);
        new FetchMoviesTask(this, mMovieAdapter).execute(sortOrderPref);
    }
}
