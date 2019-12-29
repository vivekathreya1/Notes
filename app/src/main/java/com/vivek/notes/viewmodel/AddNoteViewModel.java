package com.vivek.notes.viewmodel;

import android.app.Application;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.vivek.notes.db.NoteRepository;
import com.vivek.notes.model.Note;
import com.vivek.notes.utils.Constants;

public class AddNoteViewModel extends AndroidViewModel {

    private String TAG = AddNoteViewModel.class.getSimpleName();

    private NoteRepository noteRepository;
    private MutableLiveData<String> title = new MutableLiveData<>();
    private MutableLiveData<String> desc = new MutableLiveData<>();
    public MutableLiveData<String> imageFilePath = new MutableLiveData<>();
    private MutableLiveData<Boolean> noteCreated = new MutableLiveData<>();

    public AddNoteViewModel(@NonNull Application application) {
        super(application);
        noteRepository = new NoteRepository(application);
    }


    public void createNote(View view){

        if(imageFilePath.getValue() == null){
            imageFilePath.setValue(Constants.NO_IMAGE);
        }

       long id =  noteRepository.insert(new Note(System.currentTimeMillis(), title.getValue(), desc.getValue(), imageFilePath.getValue()));
       if(id > 0){
           noteCreated.setValue(true);
       }else{
           noteCreated.setValue(false);
       }

    }

    public MutableLiveData<String> getTitle() {
        return title;
    }


    public MutableLiveData<String> getDesc() {
        return desc;
    }


    public MutableLiveData<Boolean> getNoteCreated() {
        return noteCreated;
    }
}
