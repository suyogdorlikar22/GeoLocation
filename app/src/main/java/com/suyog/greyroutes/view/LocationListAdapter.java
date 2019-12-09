package com.suyog.greyroutes.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.BackgroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.suyog.greyroutes.R;
import com.suyog.greyroutes.app.ImageSaver;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class LocationListAdapter extends RecyclerView.Adapter<LocationListAdapter.TasksViewHolder> {

    public  static String TAG= LocationListAdapter.class.getSimpleName();
    private Context mCtx;
    private List<Location> locationList;
    String lat, lan;

    public LocationListAdapter(Context mCtx, List<Location> locationList,String lat, String lan) {
        this.mCtx = mCtx;
        this.locationList = locationList;
        this.lan = lan;
        this.lat = lat;
    }

    @Override
    public TasksViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.recyclerview_tasks, parent, false);
        return new TasksViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TasksViewHolder holder, int position) {
        Location t = locationList.get(position);

        Log.d(TAG, "onBindViewHolder: "+t.getLan()+"   "+t.getLat());
        Bitmap bitmap = new ImageSaver(mCtx).
                setFileName(t.getPath()).
                setDirectoryName("images").
                load();
        holder.imageView.setImageBitmap(bitmap);
        holder.latlan.setText("Latitude "+t.getLan()+" \n Longitude "+t.getLat());
//        holder.textViewTask.setText(t.getTask());
//        holder.textViewDesc.setText(t.getDesc());
//        holder.textViewFinishBy.setText(t.getFinishBy());

       for (int i=0;i>locationList.size();i++){

           if (lat==t.getLat()){
               setHighLightedText(holder.latlan,lan);
           }

       }

    }

    @Override
    public int getItemCount() {
        return locationList.size();
    }

    class TasksViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        ImageView imageView;
        TextView latlan;
        public TasksViewHolder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.image);
            latlan = itemView.findViewById(R.id.latlan);
//            textViewTask = itemView.findViewById(R.id.textViewTask);
//            textViewDesc = itemView.findViewById(R.id.textViewDesc);
//            textViewFinishBy = itemView.findViewById(R.id.textViewFinishBy);


            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Location location = locationList.get(getAdapterPosition());


            Intent intent = new Intent(mCtx, MapsActivity.class);
            intent.putExtra("lat", location.getLan());
            intent.putExtra("lon", location.getLat());
            mCtx.startActivity(intent);
            Log.d(TAG, "onClicasdsdk: "+ location);

        }
    }

    public void setHighLightedText(TextView tv, String textToHighlight) {
        String tvt = tv.getText().toString();
        int ofe = tvt.indexOf(textToHighlight, 0);
        Spannable wordToSpan = new SpannableString(tv.getText());
        for (int ofs = 0; ofs < tvt.length() && ofe != -1; ofs = ofe + 1) {
            ofe = tvt.indexOf(textToHighlight, ofs);
            if (ofe == -1)
                break;
            else {
                // set color here
                wordToSpan.setSpan(new BackgroundColorSpan(0xFFFFFF00), ofe, ofe + textToHighlight.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                tv.setText(wordToSpan, TextView.BufferType.SPANNABLE);
            }
        }
    }
}