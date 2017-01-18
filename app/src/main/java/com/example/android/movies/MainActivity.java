package com.example.android.movies;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.movies.utilities.MovieJsonUtils;
import com.example.android.movies.utilities.NetworkUtils;

import org.json.JSONException;

import java.io.IOException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private TextView mMovieData;
    private TextView mErrorMessage;
    private ProgressBar mLoadingIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMovieData = (TextView) findViewById(R.id.tv_movie_results_json);
        mErrorMessage = (TextView) findViewById(R.id.tv_error_message_display);
        mLoadingIndicator = (ProgressBar) findViewById(R.id.pb_loading_indicator);

        loadMovieData();
    }

    private void loadMovieData() {
        new FetchMoviesTask().execute(NetworkUtils.POPULAR_TAG);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.action_search_popular) {
            Toast.makeText(this, "Popular selected", Toast.LENGTH_SHORT).show();
            return true;
        }
        if(item.getItemId() == R.id.action_search_top_rated) {
            Toast.makeText(this, "Top Rated selected", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public class FetchMoviesTask extends AsyncTask<String, Void, String[]> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mLoadingIndicator.setVisibility(View.VISIBLE);
        }

        @Override
        protected String[] doInBackground(String... params) {
            if (params.length == 0)
                return null;

            String tag = params[0];
            URL movieRequestUrl = NetworkUtils.buildURL(tag);

            try {
                String jsonMovieResponse = NetworkUtils
                        .getResponseFromHttpUrl(movieRequestUrl);

                String[] simpleJsonMovieData = MovieJsonUtils
                        .getSimpleMovieStringsFromJson(MainActivity.this, jsonMovieResponse);
                return simpleJsonMovieData;
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String[] movieData) {
            mLoadingIndicator.setVisibility(View.INVISIBLE);
            if(movieData != null) {
                showJsonDataView();
                for (String movie : movieData)
                    mMovieData.append(movie + "\n\n");
            } else
                showErrorMessage();
        }
    }

    private void showJsonDataView() {
        mErrorMessage.setVisibility(View.INVISIBLE);
        mMovieData.setVisibility(View.VISIBLE);
    }

    private void showErrorMessage() {
        mMovieData.setVisibility(View.INVISIBLE);
        mErrorMessage.setVisibility(View.VISIBLE);
    }
}
