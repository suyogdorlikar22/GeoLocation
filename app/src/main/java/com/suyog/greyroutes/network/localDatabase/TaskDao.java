package com.suyog.greyroutes.network.localDatabase;

import com.suyog.greyroutes.view.Location;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface TaskDao {

    @Query("SELECT * FROM Location")
    List<Location> getAll();

    @Insert
    void insert(Location location);

//    @Delete
//    void delete(Location location);
//
//    @Update
//    void update(Location location);

}