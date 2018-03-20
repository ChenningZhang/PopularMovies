package com.chenning.movies.popularmovie;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chenning.movies.popularmovie.Task.FetchPostersTask;

import java.util.ArrayList;

/**
 * Created by chenningzhang on 2/5/18.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapterViewHolder> {

    private ArrayList<MovieObject> movieObjects;
    private final MovieAdapterOnClickListner movieAdapterOnClickListner;

    public MovieAdapter(MovieAdapterOnClickListner onClickListner) {
        movieAdapterOnClickListner = onClickListner;
    }

    @Override
    public MovieAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.griditem_poster, parent, false);
        MovieAdapterViewHolder movieAdapterViewHolder = new MovieAdapterViewHolder(view, this);
        return movieAdapterViewHolder;
    }

    @Override
    public void onBindViewHolder(MovieAdapterViewHolder holder, int position) {
        String posterPath = movieObjects.get(position).getPosterPath();
        new FetchPostersTask(holder.mPosterImageView).execute(posterPath);
    }

    @Override
    public int getItemCount() {
        if (movieObjects == null) {
            return 0;
        } else {
            return movieObjects.size();
        }
    }

    public void setMovieObjects(ArrayList<MovieObject> movies) {
        movieObjects = movies;
        notifyDataSetChanged();
    }

    public ArrayList<MovieObject> getMovieObjects() {
        return movieObjects;
    }

    public MovieAdapterOnClickListner getMovieAdapterOnClickListner() {
        return movieAdapterOnClickListner;
    }
}
