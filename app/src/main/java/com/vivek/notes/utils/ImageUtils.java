package com.vivek.notes.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.os.Environment;
import android.util.Pair;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ImageUtils {
    public static Bitmap scaleImage(Bitmap bitmap) {
        Bitmap newBitmap = null;
        try {
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            int newWidth = 500;
            int newHeight = 500;
            float scaleWidth = ((float) newWidth) / width;
            float scaleHeight = ((float) newHeight) / height;
            Matrix matrix = new Matrix();
            matrix.postScale(scaleWidth, scaleHeight);
            matrix.postRotate(90);
            newBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, false);
        } catch (Exception e) {
            e.printStackTrace();
        }

        bitmap.recycle();

        return newBitmap;
    }

    public static Pair<File, String> createImageFile(Context context) throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );
        return new Pair<>(image, image.getAbsolutePath());
    }

}
