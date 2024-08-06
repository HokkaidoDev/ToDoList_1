package com.hokaido.todolist_6_rxjava_myrealization;

import android.app.Application;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Note.class}, version = 1, exportSchema = false)
public abstract class NotesDatabase extends RoomDatabase {

    private static NotesDatabase instance = null;
    private final static String DB_NAME = "notes.db";

    public static NotesDatabase getInstance(Application application){
        if(instance == null){
            instance = Room.databaseBuilder(application, NotesDatabase.class, DB_NAME)
                    //.allowMainThreadQueries()
                    .build();
        }
        return instance;
    }

    public abstract NotesDao notesDao();

}
