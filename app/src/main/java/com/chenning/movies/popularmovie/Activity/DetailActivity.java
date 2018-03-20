package com.chenning.movies.popularmovie.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.chenning.movies.popularmovie.Task.FetchPostersTask;
import com.chenning.movies.popularmovie.MovieObject;
import com.chenning.movies.popularmovie.R;

public class DetailActivity extends AppCompatActivity {

    private final static String TAG = DetailActivity.class.getSimpleName();
    private ImageView mPosterImageView;
    private TextView mDateTextView;
    private TextView mRatingTextView;
    private TextView mTitleTextView;
    private TextView mOverviewTextView;
    private MovieObject mMovieObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mPosterImageView = (ImageView) findViewById(R.id.iv_detail_poster);
        mDateTextView = (TextView) findViewById(R.id.tv_detail_date);
        mRatingTextView = (TextView) findViewById(R.id.tv_detail_rating);
        mTitleTextView = (TextView) findViewById(R.id.tv_detail_title);
        mOverviewTextView = (TextView) findViewById(R.id.tv_detail_overview);

        Intent originationIntent = getIntent();
        if (originationIntent != null) {
            mMovieObject = (MovieObject) originationIntent.getSerializableExtra(MainActivity.DETAIL_INTENT_TAG);
            if (mMovieObject != null) {
                mDateTextView.setText(mMovieObject.getReleaseDate());
                mRatingTextView.setText(String.valueOf(mMovieObject.getRating()));
                mTitleTextView.setText(mMovieObject.getTitle());
                mOverviewTextView.setText(mMovieObject.getOverview());
                new FetchPostersTask(mPosterImageView).execute(mMovieObject.getPosterPath());
            }
        }
    }
}
