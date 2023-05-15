package com.example.myroomdatabase;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Person {
    @PrimaryKey(autoGenerate = true)
    public  int uid;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "idade")
    public String idade;

    public Person(String name, String idade) {
        this.name = name;
        this.idade = idade;
    }
}
