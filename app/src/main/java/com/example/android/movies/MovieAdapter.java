package com.example.android.movies;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.movies.utilities.NetworkUtils;
import com.squareup.picasso.Picasso;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieAdapterViewHolder>{
    private final ListItemClickListener mOnClickListener;
    private Movie[] mMovieData;
    private Context context;

    public MovieAdapter(ListItemClickListener listItemClickListener) {
        mOnClickListener = listItemClickListener;
    }

    @Override
    public MovieAdapterViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.movie_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachParentImmediately);
        MovieAdapterViewHolder viewHolder = new MovieAdapterViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MovieAdapterViewHolder viewHolder, int position) {
        String posterUrl = NetworkUtils.MOVIE_DB_IMAGE_BASE_URL + mMovieData[position].getPosterPath();
        Log.v("POSTER_URL", posterUrl);
        Picasso.with(context).load(posterUrl).into(viewHolder.mListItemPoster);
        viewHolder.mListItemTitle.setText("");

    }

    @Override
    public int getItemCount() {
        if(mMovieData == null)
            return 0;
        else
            return mMovieData.length;
    }

    public void setMovieData(Movie[] movieData) {
        mMovieData = movieData;
        notifyDataSetChanged();
    }

    public class MovieAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mListItemTitle;
        private ImageView mListItemPoster;

        public MovieAdapterViewHolder(View itemView) {
            super(itemView);
            mListItemPoster = (ImageView) itemView.findViewById(R.id.iv_item_poster);
            mListItemTitle = (TextView) itemView.findViewById(R.id.tv_item_title);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            mOnClickListener.onListItemClick(clickedPosition);
        }
    }

    public interface ListItemClickListener {

        void onListItemClick(int listItemIndex);
    }

}
