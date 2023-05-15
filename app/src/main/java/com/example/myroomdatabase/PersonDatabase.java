package com.example.myroomdatabase;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Person.class},version = 1)
public abstract class PersonDatabase extends RoomDatabase {
    public abstract  PersonDao personDao();
}
