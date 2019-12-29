package com.vivek.notes.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BindingAdapters {

    @BindingAdapter({"bind:imagePath"})
    public static void loadImage(ImageView view, String imagePath) {
        if(imagePath!=null){
            Matrix matrix = new Matrix();
            matrix.postRotate(90);
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
            Bitmap rotatedBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
            view.setImageBitmap(rotatedBitmap);
        }
    }

    @BindingAdapter({"bind:dateTime"})
    public static void showDateTimeString(TextView view, Long creationTime){
        Date date = new Date(creationTime);
        SimpleDateFormat df = new SimpleDateFormat("dd MMM yyyy HH:mm a");
        view.setText(df.format(date));
    }
}
