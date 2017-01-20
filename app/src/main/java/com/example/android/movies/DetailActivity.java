package com.example.android.movies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.movies.utilities.NetworkUtils;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    private TextView mTitle;
    private TextView mOriginalTitle;
    private TextView mReleaseDate;
    private TextView mSynopsis;
    private TextView mUserRating;
    private ImageView mPoster;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mTitle = (TextView) findViewById(R.id.tv_detail_title);
        mReleaseDate = (TextView) findViewById(R.id.tv_detail_release_date);
        mSynopsis = (TextView) findViewById(R.id.tv_detail_synopsis);
        mUserRating = (TextView) findViewById(R.id.tv_detail_rating);
        mPoster = (ImageView) findViewById(R.id.iv_detail_poster);

        Intent intentThatStartedThisActivity = getIntent();

        if (intentThatStartedThisActivity.hasExtra(Intent.EXTRA_TEXT)) {
            Movie movie= (Movie) intentThatStartedThisActivity.getSerializableExtra(Intent.EXTRA_TEXT);

            String posterUrl = NetworkUtils.MOVIE_DB_IMAGE_BASE_URL + movie.getPosterPath();
            Picasso.with(this).load(posterUrl).into(mPoster);
            mReleaseDate.setText("Release Date:\n\t" + movie.getReleaseDate().substring(0, 4));
            if(movie.getTitle().equals(movie.getOriginalTitle()))
                mTitle.setText("\nTitle:\n\t" + movie.getTitle());
            else
                mTitle.setText("\nTitle:\n\t" + movie.getTitle() + " (" + movie.getOriginalTitle() + ")");
            mSynopsis.setText("\nSynopsis:\n\t" + movie.getOverview());
            mUserRating.setText("\nUser Rating:\n\t" + movie.getUserRating());
        }
    }
}
