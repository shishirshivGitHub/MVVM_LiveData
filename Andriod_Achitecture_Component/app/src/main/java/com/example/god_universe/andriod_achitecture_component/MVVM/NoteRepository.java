package com.example.god_universe.andriod_achitecture_component.MVVM;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class NoteRepository {


    private Note_Data_Access_Object noteDataAccessObject;
    private LiveData<List<Note>> allNotes;

    public NoteRepository(Application application){
        NoteDatabase noteDatabase = NoteDatabase.getInstance(application);
        noteDataAccessObject = noteDatabase.note_data_access_object();
        allNotes = noteDataAccessObject.getAllNotes();
    }

    public void insert(Note note){
        new InsertNoteAsyncTask(noteDataAccessObject).execute(note);
    }

    public void update(Note note){
        new UpdateNoteAsyncTask(noteDataAccessObject).execute(note);
    }

    public void delete(Note note){
        new DeleteNoteAsyncTask(noteDataAccessObject).execute(note);
    }

    public void deleteAllNotes(){
        new DeleteAllNotesAsyncTask(noteDataAccessObject).execute();
    }

    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }

    private static class InsertNoteAsyncTask extends AsyncTask<Note,Void,Void>{
        Note_Data_Access_Object noteDataAccessObject;

        private InsertNoteAsyncTask(Note_Data_Access_Object note_data_access_object){
            this.noteDataAccessObject = note_data_access_object;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDataAccessObject.insert(notes[0  ]);
            return null;
        }
    }

    private static class UpdateNoteAsyncTask extends AsyncTask<Note,Void,Void>{
        Note_Data_Access_Object noteDataAccessObject;

        private UpdateNoteAsyncTask(Note_Data_Access_Object note_data_access_object){
            this.noteDataAccessObject = note_data_access_object;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDataAccessObject.update(notes[0]);
            return null;
        }
    }

    private static class DeleteNoteAsyncTask extends AsyncTask<Note,Void,Void>{
        Note_Data_Access_Object noteDataAccessObject;

        private DeleteNoteAsyncTask(Note_Data_Access_Object note_data_access_object){
            this.noteDataAccessObject = note_data_access_object;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDataAccessObject.delete(notes[0]);
            return null;
        }
    }

    private static class DeleteAllNotesAsyncTask extends AsyncTask<Void,Void,Void>{
        Note_Data_Access_Object noteDataAccessObject;

        private DeleteAllNotesAsyncTask(Note_Data_Access_Object note_data_access_object){
            this.noteDataAccessObject = note_data_access_object;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            noteDataAccessObject.deleteAllNotes();
            return null;
        }
    }


}
