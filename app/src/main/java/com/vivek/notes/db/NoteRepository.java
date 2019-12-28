package com.vivek.notes.db;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.vivek.notes.model.Note;

import java.util.List;

public class NoteRepository {
    private NoteDao noteDao;

    public NoteRepository(Context context){
        AppDatabase db = AppDatabase.getDbInstance(context);
        noteDao = db.noteDao();
    }

    public LiveData<List<Note>> getAllNotesInDesc() {
        return noteDao.getNoteListinDesc();
    }

    public LiveData<List<Note>> getSearchResult(String searchString) {
        return noteDao.getSearchResult(searchString);
    }

    public LiveData<List<Note>> getAllNotesInAsc() {
        return noteDao.getNoteListInAsc();
    }

    public Long insert(Note note) {
        InsertAsyncTask task = new InsertAsyncTask(noteDao);
        Long id = null;
        try {
            id = task.execute(note).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }

    private static class InsertAsyncTask extends AsyncTask<Note, Void, Long> {
        private NoteDao mAsyncTaskDao;

        InsertAsyncTask(NoteDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Long doInBackground(final Note... params) {
            return mAsyncTaskDao.insert(params[0]);
        }
    }

}
