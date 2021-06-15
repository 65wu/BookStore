package com.example.bookstore.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class FileHelper {
    public void saveImage(Context context, Bitmap bitmap, String name){
        name = name + ".png";
        FileOutputStream fileOutputStream;
        try {
            fileOutputStream = context.openFileOutput(name, Context.MODE_PRIVATE);
//            Log.d("路径是", context.getFileStreamPath("name").toString());
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, fileOutputStream);
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public Bitmap loadImageBitmap(Context context,String type, String id){
        id = type + "_" + id + ".png";
        FileInputStream fileInputStream;
        Bitmap bitmap = null;
        try{
            fileInputStream = context.openFileInput(id);
            bitmap = BitmapFactory.decodeStream(fileInputStream);
            fileInputStream.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }
}
