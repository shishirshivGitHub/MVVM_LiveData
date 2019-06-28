package com.example.god_universe.andriod_achitecture_component.MVVM;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

@Database(entities = {Note.class},version = 1)
public abstract class NoteDatabase extends RoomDatabase {



    private static NoteDatabase instance;

    public abstract Note_Data_Access_Object note_data_access_object();

    public static synchronized NoteDatabase getInstance(Context context){

        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    NoteDatabase.class,"note_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void,Void,Void>{

        private Note_Data_Access_Object noteDao;

        private PopulateDbAsyncTask(NoteDatabase noteDatabase){
            noteDao = noteDatabase.note_data_access_object();
        }

        @Override
        protected Void doInBackground(Void... voids) {
                noteDao.insert(new Note("Hello GOD","Is everything is fine God !!",1));
            return null;
        }
    }

}
