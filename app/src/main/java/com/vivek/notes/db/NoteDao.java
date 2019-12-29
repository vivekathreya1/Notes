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

    @Query("SELECT * from noteTable ORDER BY creationTime DESC ")
    LiveData<List<Note>> getNoteListinDesc();

    @Query("SELECT * from noteTable where title LIKE :searchString OR description LIKE:searchString ORDER BY creationTime DESC")
    List<Note> getSearchResult(String searchString);

    @Query("SELECT * FROM noteTable ORDER BY CASE WHEN :isAsc = 1 THEN creationTime END ASC, CASE WHEN :isAsc = 0 THEN creationTime END DESC")
    LiveData<List<Note>> getNoteList(boolean isAsc);


}
