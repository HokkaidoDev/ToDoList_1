package com.hokaido.todolist_6_rxjava_myrealization;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

public class MainViewModel extends AndroidViewModel {

    private NotesDao notesDao;

    public MainViewModel(@NonNull Application application) {
        super(application);
        notesDao = NotesDatabase.getInstance(application).notesDao();
    }

    public LiveData<List<Note>> getNotes(){
       return notesDao.getNotes();
    }

    public void remove(Note note){
        new Thread(() -> notesDao.remove(note.getId())).start();
    }


}
