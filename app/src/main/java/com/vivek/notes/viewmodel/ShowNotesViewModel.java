package com.vivek.notes.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.vivek.notes.db.NoteRepository;
import com.vivek.notes.model.Note;

import java.util.List;

public class ShowNotesViewModel extends AndroidViewModel {

    private LiveData<List<Note>> allNotes;
    private NoteRepository noteRepository;
    private boolean asc ;
    private String mediaOnly;

    private MutableLiveData<Boolean> oldestClick = new MutableLiveData<>();
    private MutableLiveData<Boolean> newestClick = new MutableLiveData<>();
    private MutableLiveData<Boolean> allNotesClick = new MutableLiveData<>();
    private MutableLiveData<Boolean> mediaOnlyClick = new MutableLiveData<>();

    public ShowNotesViewModel(@NonNull Application application) {
        super(application);
        noteRepository = new NoteRepository(application);
    }

    public void getDataFromDb(boolean isAsc, String mediaOnly){
        asc = isAsc;
        this.mediaOnly = mediaOnly;
        allNotes = noteRepository.getAllNotes(isAsc);
    }

    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }

  /*  public void oldestClicked(){
        allNotes = noteRepository.getAllNotes(true);
    }*/

    public MutableLiveData<Boolean> getOldestClicked() {
        return oldestClick;
    }

    public MutableLiveData<Boolean> getNewestClick() {
        return newestClick;
    }



    public void newestClicked(){
        newestClick.setValue(true);
    }

    public void oldestClicked(){
        oldestClick.setValue(true);
    }

    public void allNotesClicked(){
        allNotesClick.setValue(true);
    }
    public void mediaOnlyClicked(){
        mediaOnlyClick.setValue(true);
    }

    public MutableLiveData<Boolean> getAllNotesClick() {
        return allNotesClick;
    }

    public MutableLiveData<Boolean> getMediaOnlyClick() {
        return mediaOnlyClick;
    }
}
