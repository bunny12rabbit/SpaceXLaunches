package com.BunnyRabbit.SpaceXLaunches;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "RecyclerViewAdapter";

    private ArrayList<Launches> mLaunches;
    private Context mContext;

    public RecyclerViewAdapter(Context mContext, ArrayList<Launches> launches) {
        this.mContext = mContext;
        this.mLaunches = launches;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_cardvew, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        Log.d(TAG, "onBindViewHolder: called.");

        final Launches currentItem = mLaunches.get(position);

        MainActivity.progressBar.setVisibility(View.VISIBLE);

        Glide.with(mContext)
                .asBitmap()
                .load(currentItem.getMission_patch())
                .into(viewHolder.mission_path);
        viewHolder.mission_name.setText(currentItem.getMission_name());
        viewHolder.launch_date_utc.setText(currentItem.getLaunch_date_utc());
        viewHolder.details.setText(currentItem.getDetails());
        viewHolder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: clicked on:" + currentItem.getMission_name());
                //Событие по нажатию на элемент списка
                Toast.makeText(mContext,currentItem.getMission_name(), Toast.LENGTH_SHORT).show();
            }
        });

        MainActivity.progressBar.setVisibility(View.GONE);
    }

    @Override
    public int getItemCount() {
        return mLaunches.size();
    }

    public class  ViewHolder extends RecyclerView.ViewHolder{

        ImageView mission_path;
        TextView mission_name;
        TextView launch_date_utc;
        TextView details;
        RelativeLayout parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mission_path = itemView.findViewById(R.id. image_mission_patch);
            mission_name = itemView.findViewById(R.id.text_mission_name);
            launch_date_utc = itemView.findViewById(R.id.text_launch_date_utc);
            details = itemView.findViewById(R.id.text_details);
            parentLayout = itemView.findViewById(R.id.parent_layout);
        }
    }
}
