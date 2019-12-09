package com.suyog.greyroutes.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.suyog.greyroutes.R;
import com.suyog.greyroutes.network.localDatabase.DatabaseClient;

import java.util.List;

public class ListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    String lon,lat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        recyclerView = findViewById(R.id.recyclerView);

        if (getIntent() !=null) {
            lon = getIntent().getStringExtra("lon");
            lat = getIntent().getStringExtra("lat");
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(this));




        getTasks();
    }

    private void getTasks() {
        class GetTasks extends AsyncTask<Void, Void, List<Location>> {

            @Override
            protected List<Location> doInBackground(Void... voids) {
                List<Location> locationList = DatabaseClient
                        .getInstance(getApplicationContext())
                        .getAppDatabase()
                        .taskDao()
                        .getAll();
                return locationList;
            }

            @Override
            protected void onPostExecute(List<Location> tasks) {
                super.onPostExecute(tasks);
                Log.d("Suyog", "onPostExecute: "+tasks);
                LocationListAdapter adapter = new LocationListAdapter(ListActivity.this, tasks,lat,lon);
                recyclerView.setAdapter(adapter);
            }
        }

        GetTasks gt = new GetTasks();
        gt.execute();
    }

}
