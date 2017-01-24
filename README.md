# TMDbOnAndroid
An android version of the Movie Database (TMDb), a community built Movie and TV database dating back to 2008.
Displays the top 20 most popular movies as voted by the community.

**NOTE:** Before being able to run this application, a new API key will need to be input.

1. Navigate to www.theMovieDB.org and register a new educational/non-commercial account.
2. Replace the API_KEY field in MoviePreferences with the one that was generated on the webpage.
3. Enjoy the app!

......................................................................................................................................

**HTTP Requests:**

Popular Movies
https://api.themoviedb.org/3/movie/popular?api_key={API_KEY}

Top Rated movies
https://api.themoviedb.org/3/movie/top_rated?api_key={API_KEY}

Trailers
https://api.themoviedb.org/3/movie/{MOVIE_ID}/videos?api_key={API_KEY}

......................................................................................................................................

**CURL Request:**
curl --request GET --url 'https://api.themoviedb.org/3/movie/32811/videos?api_key={API_KEY}' --data '{}'