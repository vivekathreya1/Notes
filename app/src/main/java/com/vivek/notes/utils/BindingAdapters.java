package com.vivek.notes.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BindingAdapters {

    @BindingAdapter({"bind:imagePath"})
    public static void loadImage(ImageView view, String imagePath) {
        Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
        view.setImageBitmap(bitmap);
    }

    @BindingAdapter({"bind:dateTime"})
    public static void showDateTimeString(TextView view, Long creationTime){
        Date date = new Date(creationTime);
        SimpleDateFormat df = new SimpleDateFormat("dd MMM yyyy HH:mm a");
        view.setText(df.format(date));
    }
}
