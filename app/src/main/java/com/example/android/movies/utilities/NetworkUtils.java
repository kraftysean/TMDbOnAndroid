package com.example.android.movies.utilities;

import android.net.Uri;
import android.util.Log;

import com.example.android.movies.data.MoviePreferences;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class NetworkUtils {

    private final static String TAG = NetworkUtils.class.getSimpleName();

    public final static String MOVIE_DB_BASE_URL = "https://api.themoviedb.org/3/movie/";
    public final static String MOVIE_DB_IMAGE_BASE_URL = "http://image.tmdb.org/t/p/w185/";
    public final static String POPULAR_ENDPOINT = "popular";
    public final static String TOP_RATED_ENDPOINT = "top_rated";

    final static String API_KEY = "api_key";
    final static String PAGE = "page";
    /**
     * This method returns the entire result from the HTTP response.
     *
     * @param endpoint The sort order required to fetch the HTTP response from.
     * @return The contents of the HTTP response.
     * @throws IOException Related to network and stream reading
     */

    public static URL buildURL(String endpoint) {
        Uri builltUri = Uri.parse(MOVIE_DB_BASE_URL + endpoint).buildUpon()
                .appendQueryParameter(API_KEY, MoviePreferences.API_KEY)
                .build();

        URL url = null;
        try {
            url = new URL(builltUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        Log.v(TAG, "Built URI " + url);
        return url;
    }

    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
}