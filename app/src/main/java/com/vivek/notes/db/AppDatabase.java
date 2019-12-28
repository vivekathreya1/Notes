package com.vivek.notes.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.vivek.notes.model.Note;

@Database(entities = {Note.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase dbInstance;
    public abstract NoteDao noteDao();

    public static AppDatabase getDbInstance(Context context){
        if(dbInstance == null){
            dbInstance = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "note-db").build();
        }
        return dbInstance;
    }



}
