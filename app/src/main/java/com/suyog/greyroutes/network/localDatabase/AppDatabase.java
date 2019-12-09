package com.suyog.greyroutes.network.localDatabase;

import com.suyog.greyroutes.view.Location;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Location.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract TaskDao taskDao();
}