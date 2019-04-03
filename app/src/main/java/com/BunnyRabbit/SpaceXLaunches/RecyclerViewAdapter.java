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

    private ArrayList<String> mMission_name;
    private ArrayList<String> mMission_path;
    private ArrayList<String> mLaunch_date_utc;
    private ArrayList<String> mDetails;
    private Context mContext;

    public RecyclerViewAdapter(Context mContext, ArrayList<String> mMission_path, ArrayList<String> mMission_name,
                               ArrayList<String> mLaunch_date_utc, ArrayList<String> mDetails) {
        this.mContext = mContext;
        this.mMission_path = mMission_path;
        this.mMission_name = mMission_name;
        this.mLaunch_date_utc = mLaunch_date_utc;
        this.mDetails = mDetails;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_listitem, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        Log.d(TAG, "onBindViewHolder: called.");

        Glide.with(mContext)
                .asBitmap()
                .load(mMission_path.get(i))
                .into(viewHolder.mission_path);
        viewHolder.mission_name.setText(mMission_name.get(i));
        viewHolder.launch_date_utc.setText(mLaunch_date_utc.get(i));
        viewHolder.details.setText(mDetails.get(i));
        viewHolder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: clicked on:" + mMission_name.get(i));
                //Событие по нажатию на элемент списка
                Toast.makeText(mContext, mMission_name.get(i), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mMission_name.size();
    }

    public class  ViewHolder extends RecyclerView.ViewHolder{

        ImageView mission_path;
        TextView mission_name;
        TextView launch_date_utc;
        TextView details;
        RelativeLayout parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mission_path = itemView.findViewById(R.id.mission_path);
            mission_name = itemView.findViewById(R.id.mission_name);
            launch_date_utc = itemView.findViewById(R.id.launch_date_utc);
            details = itemView.findViewById(R.id.details);
            parentLayout = itemView.findViewById(R.id.parent_layout);
        }
    }
}
