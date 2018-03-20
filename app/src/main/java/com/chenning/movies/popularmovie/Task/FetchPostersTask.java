package com.chenning.movies.popularmovie.Task;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import com.chenning.movies.popularmovie.Utils.NetworkUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * Created by chenningzhang on 2/5/18.
 */

public class FetchPostersTask extends AsyncTask<String, Void, Bitmap> {

    private static final String TAG = FetchPostersTask.class.getSimpleName();
    private ImageView mImageView;

    public FetchPostersTask(ImageView imageView) {
        mImageView = imageView;
    }

    @Override
    protected Bitmap doInBackground(String... strings) {
        String posterPath = strings[0];
        URL fetchPostersUrl = NetworkUtils.buildFetchPosterUrlWithDefaultSize(posterPath);
        try {
            InputStream inputStream = fetchPostersUrl.openStream();
            Bitmap posterImage = BitmapFactory.decodeStream(inputStream);
            return posterImage;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        if (bitmap != null) {
            mImageView.setImageBitmap(bitmap);
        }
    }
}
