package com.gridants.photoapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by cube on 10/12/15.
 */
public class mRecyclerAdapter extends RecyclerView.Adapter<mRecyclerAdapter.ViewHolder> {

    Context context;

    public mRecyclerAdapter (Context context){
        this.context = context;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        public ViewHolder(View v){
            super(v);
            image = (ImageView) v.findViewById(R.id.images);
        }
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.photo_grid, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {


    }


    @Override
    public int getItemCount() {
        return 0;
    }
}
