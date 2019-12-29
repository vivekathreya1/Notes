package com.vivek.notes.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.vivek.notes.db.NoteRepository;
import com.vivek.notes.model.Note;

import java.util.List;

public class SearchViewModel extends AndroidViewModel {

    private MutableLiveData<List<Note>> searchedNotes = new MutableLiveData<>();
    private NoteRepository noteRepository;
    private String TAG = SearchViewModel.class.getSimpleName();

    public SearchViewModel(@NonNull Application application) {
        super(application);
        noteRepository = new NoteRepository(application);
    }
    public void searchNotes(String query){
        Log.e(TAG, "SearchViewModel: searchNotes ");
//        searchedNotes = noteRepository.getSearchResult(query);
        searchedNotes.setValue(noteRepository.getSearchResults(query));
    }

    public MutableLiveData<List<Note>> getSearchedNotes() {
        return searchedNotes;
    }
}
