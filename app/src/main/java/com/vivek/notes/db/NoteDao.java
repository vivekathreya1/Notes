package com.vivek.notes.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.vivek.notes.model.Note;

import java.util.List;

@Dao
public interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long insert(Note note);

    /*@Query("SELECT * from noteTable WHERE NULLIF(imagePath,:imagePath) ORDER BY creationTime DESC ")
    LiveData<List<Note>> getNoteListinDesc(String imagePath);*/

    @Query("SELECT * from noteTable where title LIKE :searchString OR description LIKE:searchString ORDER BY creationTime DESC")
    List<Note> getSearchResult(String searchString);

    @Query("SELECT * FROM noteTable")
    LiveData<List<Note>> getNoteList();


}
