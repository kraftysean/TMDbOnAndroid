package com.example.android.movies.utilities;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class MovieJsonUtils {

    public static String[] getSimpleMovieStringsFromJson(Context context, String movieJsonStr) throws JSONException {
        /* String array to hold data about each movie */
        String[] parsedMovieData = null;

        JSONObject moviesJson = new JSONObject(movieJsonStr);

        JSONArray moviesArray = moviesJson.getJSONArray("results");

        parsedMovieData = new String[moviesArray.length()];

        for(int i = 0; i < moviesArray.length(); i++) {

            /* These are the values that will be collected */
            String title;
            String overview;
            String posterPath;
            String originalTitle;
            String releaseDate;
            String userRating;
            JSONObject movieObject = moviesArray.getJSONObject(i);

            title = movieObject.getString("title");
            overview = movieObject.getString("overview");
            posterPath = movieObject.getString("poster_path");
            originalTitle = movieObject.getString("original_title");
            releaseDate = movieObject.getString("release_date");
            userRating = movieObject.getString("vote_average");

            parsedMovieData[i] = title + ": " + overview + "\n" + posterPath;
        }

        return parsedMovieData;
    }
}
