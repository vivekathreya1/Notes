package com.vivek.notes.db;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.vivek.notes.model.Note;

import java.util.List;

public class NoteRepository {
    private NoteDao noteDao;

    public NoteRepository(Context context) {
        AppDatabase db = AppDatabase.getDbInstance(context);
        noteDao = db.noteDao();
    }

    public LiveData<List<Note>> getAllNotes() {

        return noteDao.getNoteList();
//        return noteDao.getNoteListinDesc("");
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


    public List<Note> getSearchResults(String query) {
        SearchAsyncTask task = new SearchAsyncTask(noteDao);
        List<Note> noteList = null;
        try {
            noteList = task.execute(query).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return noteList;
    }

    private static class SearchAsyncTask extends AsyncTask<String, Void, List<Note>> {
        private NoteDao mAsyncTaskDao;

        SearchAsyncTask(NoteDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected List<Note> doInBackground(String... strings) {
            return mAsyncTaskDao.getSearchResult(strings[0]);
        }
    }

}
