package com.chenning.movies.popularmovie;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by chenningzhang on 2/5/18.
 */

public class MovieAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public final ImageView mPosterImageView;
    private final MovieAdapter mMovieAdapter;

    public MovieAdapterViewHolder(View itemView, MovieAdapter movieAdapter) {
        super(itemView);
        mPosterImageView = (ImageView) itemView.findViewById(R.id.iv_griditem_poster);
        mMovieAdapter = movieAdapter;
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int clickedItemPosition = getAdapterPosition();
        MovieObject currentObject = mMovieAdapter.getMovieObjects().get(clickedItemPosition);
        mMovieAdapter.getMovieAdapterOnClickListner().onClick(currentObject);
    }
}
