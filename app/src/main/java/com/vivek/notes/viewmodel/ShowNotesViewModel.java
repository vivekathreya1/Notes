package com.vivek.notes.viewmodel;

import android.app.Application;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.vivek.notes.db.NoteRepository;
import com.vivek.notes.model.Note;

import java.util.List;

public class ShowNotesViewModel extends AndroidViewModel {

    private LiveData<List<Note>> allNotes;
    private NoteRepository noteRepository;

    public ShowNotesViewModel(@NonNull Application application) {
        super(application);
        noteRepository = new NoteRepository(application);
//        allNotes = noteRepository.getAllNotesInDesc();

//        allNotes = noteRepository.getSearchResult("vivek");
    }

    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }


    public void oldestClicked(View view){
        allNotes = noteRepository.getAllNotesInAsc();
    }
    public void newestClicked(View view){
        allNotes = noteRepository.getAllNotesInDesc();
    }
}
