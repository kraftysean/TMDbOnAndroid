package com.example.android.movies;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.android.movies.utilities.MovieJsonUtils;
import com.example.android.movies.utilities.NetworkUtils;
import com.squareup.picasso.Picasso;

import org.json.JSONException;

import java.io.IOException;
import java.net.URL;

public class DetailActivity extends AppCompatActivity {

    private TextView mErrorMessage;
    private ProgressBar mLoadingIndicator;
    private ScrollView mScrollView;
    private TextView mTitle;
    private ImageView mPoster;
    private TextView mReleaseDate;
    private TextView mRuntime;
    private TextView mUserRating;
    private TextView mSynopsis;
    private Movie mMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mScrollView = (ScrollView) findViewById(R.id.sv_movie_detail);
        mErrorMessage = (TextView) findViewById(R.id.tv_detail_error_message_display);
        mLoadingIndicator = (ProgressBar) findViewById(R.id.pb_detail_loading_indicator);

        mTitle = (TextView) findViewById(R.id.tv_detail_title);
        mPoster = (ImageView) findViewById(R.id.iv_detail_poster);
        mReleaseDate = (TextView) findViewById(R.id.tv_detail_release_date);
        mRuntime = (TextView) findViewById(R.id.tv_detail_runtime);
        mUserRating = (TextView) findViewById(R.id.tv_detail_rating);
        mSynopsis = (TextView) findViewById(R.id.tv_detail_synopsis);

        Intent intentThatStartedThisActivity = getIntent();

        if (intentThatStartedThisActivity.hasExtra(Intent.EXTRA_TEXT)) {
            Movie movie = (Movie) intentThatStartedThisActivity.getSerializableExtra(Intent.EXTRA_TEXT);
            loadMovieData(movie);
        }
    }

    private void loadMovieData(Movie movie) {
        new FetchMovieDetailsTask().execute(String.valueOf(movie.getId()));
        showMovieDataView();
    }


    public void showMovieDataView() {
        mErrorMessage.setVisibility(View.INVISIBLE);
        mScrollView.setVisibility(View.VISIBLE);
    }

    private void showErrorMessage() {
        mScrollView.setVisibility(View.INVISIBLE);
        mErrorMessage.setVisibility(View.VISIBLE);
    }

    public class FetchMovieDetailsTask extends AsyncTask<String, Void, Movie> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mLoadingIndicator.setVisibility(View.VISIBLE);
        }

        @Override
        protected Movie doInBackground(String... params) {
            if (params.length == 0)
                return null;

            String endpoint = params[0];
            URL movieRequestUrl = NetworkUtils.buildURL(endpoint);

            try {
                String jsonMovieResponse = NetworkUtils
                        .getResponseFromHttpUrl(movieRequestUrl);

                mMovie = MovieJsonUtils
                        .getDetailedData(DetailActivity.this, jsonMovieResponse);

                return mMovie;
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Movie movie) {
            mLoadingIndicator.setVisibility(View.INVISIBLE);

            if (movie != null) {
                mTitle.setText(movie.getTitle());
                String posterUrl = NetworkUtils.MOVIE_DB_IMAGE_BASE_URL + movie.getPosterPath();
                Picasso.with(getApplicationContext()).load(posterUrl).into(mPoster);

                String releaseDate = movie.getReleaseDate().substring(0, 4);
                mReleaseDate.setText(releaseDate);
                String runtime = String.valueOf(movie.getRuntime()).concat("min");
                mRuntime.setText(runtime);
                String voteAverage = String.valueOf(movie.getVoteAverage()).concat("/10");
                mUserRating.setText(voteAverage);

                mSynopsis.setText(movie.getOverview());
                super.onPostExecute(movie);
            } else {
                showErrorMessage();
            }
        }
    }
}