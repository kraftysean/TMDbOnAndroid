package com.example.android.movies.utilities;

import android.content.Context;
import android.widget.ImageView;

import com.example.android.movies.Movie;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class MovieJsonUtils {

    private final static String MOVIE_DB_IMAGE_BASE_URL = "http://image.tmdb.org/t/p";

    public static Movie[] getSimpleMovieStringsFromJson(Context context, String movieJsonStr) throws JSONException {
        /* String array to hold data about each movie */
        Movie[] parsedMovieData = null;

        JSONObject moviesJson = new JSONObject(movieJsonStr);

        JSONArray moviesArray = moviesJson.getJSONArray("results");

        parsedMovieData = new Movie[moviesArray.length()];

        for(int i = 0; i < moviesArray.length(); i++) {
            JSONObject movieObject = moviesArray.getJSONObject(i);

            parsedMovieData[i] = new Movie();

            parsedMovieData[i].setTitle(movieObject.getString("title"));
            parsedMovieData[i].setOriginalTitle(movieObject.getString("original_title"));
            parsedMovieData[i].setOverview(movieObject.getString("overview"));
            parsedMovieData[i].setPosterPath(movieObject.getString("poster_path"));
            parsedMovieData[i].setReleaseDate(movieObject.getString("release_date"));
            parsedMovieData[i].setUserRating(movieObject.getString("vote_average"));
        }

        return parsedMovieData;
    }
}
