package com.example.god_universe.andriod_achitecture_component.MVVM;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

// Data Access Object may be abstract class or interface.

@Dao
public interface Note_Data_Access_Object {

    @Insert
    void insert(Note note);

    @Update
    void update(Note note);

    @Delete
    void delete(Note note);

    @Query("DELETE From note_table")
    void deleteAllNotes();

    @Query("SELECT * From note_table ORDER By priority_columun desc")
    LiveData<List<Note>> getAllNotes();   // due to live data we can observe any changes
}
