package com.vivek.notes.model;

import androidx.annotation.NonNull;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "noteTable")
public class Note extends BaseObservable {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "creationTime")
    private Long creationTime;
    @ColumnInfo(name = "title")
    private String title;
    @ColumnInfo(name = "description")
    private String description;
    @ColumnInfo(name = "imagePath")
    private String imagePath;
    @ColumnInfo(name = "imageExists")
    private Boolean imageExists;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Bindable
    public Long getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Long creationTime) {
        this.creationTime = creationTime;
        notifyPropertyChanged(BR.creationTime);
    }

    @Bindable
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        notifyPropertyChanged(BR.title);
    }

    @Bindable
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
        notifyPropertyChanged(BR.description);
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Note(Long creationTime, String title, String description, String imagePath, Boolean imageExists) {
        this.creationTime = creationTime;
        this.title = title;
        this.description = description;
        this.imagePath = imagePath;
        this.imageExists = imageExists;
    }

    public Boolean getImageExists() {
        return imageExists;
    }

    public void setImageExists(Boolean imageExists) {
        this.imageExists = imageExists;
    }
}
