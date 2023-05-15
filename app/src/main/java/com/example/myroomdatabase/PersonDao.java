package com.example.myroomdatabase;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface PersonDao {

    @Insert
    void insertAll (Person... persons);

    @Delete
    void deletePerson(Person person);

    @Update
    void editPerson(Person person);

    @Query("DELETE FROM person")
    void removeAll();

    @Query("SELECT * FROM Person")
    List<Person> getAllPerson();

}
