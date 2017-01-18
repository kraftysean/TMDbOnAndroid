package com.example.android.movies;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieAdapterViewHolder>{
    Movie[] mMovieData;

    @Override
    public MovieAdapterViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.movie_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachParentImmediately);
        MovieAdapterViewHolder viewHolder = new MovieAdapterViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MovieAdapterViewHolder viewHolder, int position) {
        Movie movieData = mMovieData[position];
        viewHolder.mListItemTitle.setText(movieData.getTitle());
    }

    @Override
    public int getItemCount() {
        if(mMovieData == null)
            return 0;
        else
            return mMovieData.length;

    }

    public  void setMovieData(Movie[] movieData) {
        mMovieData = movieData;
        notifyDataSetChanged();
    }


    public class MovieAdapterViewHolder extends RecyclerView.ViewHolder {

        private TextView mListItemTitle;

        public MovieAdapterViewHolder(View itemView) {
            super(itemView);
            mListItemTitle = (TextView) itemView.findViewById(R.id.tv_item_title);
        }
    }
}
