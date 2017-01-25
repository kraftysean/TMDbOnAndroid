package com.example.android.movies.utilities;

import android.content.Context;

import com.example.android.movies.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class MovieJsonUtils {

    public static Movie[] getSimpleData(Context context, String movieJsonStr) throws JSONException {
        JSONObject moviesJson = new JSONObject(movieJsonStr);

        JSONArray moviesArray = moviesJson.getJSONArray("results");

        Movie[] parsedMovieData = new Movie[moviesArray.length()];

        for(int i = 0; i < moviesArray.length(); i++) {
            JSONObject movieObject = moviesArray.getJSONObject(i);

            parsedMovieData[i] = new Movie();
            parsedMovieData[i].setId(movieObject.getInt("id"));
            parsedMovieData[i].setTitle(movieObject.getString("title"));
            parsedMovieData[i].setOriginalTitle(movieObject.getString("original_title"));
            parsedMovieData[i].setPosterPath(movieObject.getString("poster_path"));
        }
        return parsedMovieData;
    }

    public static Movie getDetailedData(Context context, String movieJsonStr) throws JSONException {

        JSONObject movieObject = new JSONObject(movieJsonStr);

        Movie parsedMovieData = new Movie();

        parsedMovieData.setTitle(movieObject.getString("title"));
        parsedMovieData.setOriginalTitle(movieObject.getString("original_title"));
        parsedMovieData.setPosterPath(movieObject.getString("poster_path"));
        parsedMovieData.setReleaseDate(movieObject.getString("release_date"));
        parsedMovieData.setRuntime(movieObject.getInt("runtime"));
        parsedMovieData.setVoteAverage(movieObject.getInt("vote_average"));
        parsedMovieData.setOverview(movieObject.getString("overview"));

        return parsedMovieData;
    }
}
