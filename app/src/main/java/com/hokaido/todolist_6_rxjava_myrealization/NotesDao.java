package com.hokaido.todolist_6_rxjava_myrealization;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

@Dao
public interface NotesDao {

    @Query("SELECT * FROM notes")
    //Single<List<Note>> getNotes();
    List<Note> getNotes();

//    @Insert(onConflict = OnConflictStrategy.ABORT)
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    @Insert(onConflict = OnConflictStrategy.IGNORE)
    @Insert
    //Completable add(Note note);
    void add(Note note);

    @Query("DELETE FROM notes WHERE id = :id")
    //Completable remove(int id);
    void remove(int id);
}
