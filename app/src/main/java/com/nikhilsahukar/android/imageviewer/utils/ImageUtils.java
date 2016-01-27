package com.nikhilsahukar.android.imageviewer.utils;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import com.nikhilsahukar.android.imageviewer.Object.Image;

import java.util.ArrayList;

/**
 * Created by Nikhil on 27/01/16.
 */
public class ImageUtils {

    public static ArrayList<Image> getDeviceImages(Context context) {
        ArrayList<Image> images = new ArrayList<>();

        // which image properties are we querying
        String[] projection = new String[]{
                MediaStore.Images.Media._ID,
                MediaStore.Images.Media.DISPLAY_NAME,
                MediaStore.Images.Media.DATA
        };

        // content:// style URI for the "primary" external storage volume
        Uri imagesUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;

        // Make the query.
        Cursor cur = context.getContentResolver().query(imagesUri,
                projection, // Which columns to return
                null,       // Which rows to return (all rows)
                null,       // Selection arguments (none)
                null        // Ordering
        );

        Log.i("ListingImages", " query count=" + cur.getCount());

        if (cur.moveToFirst()) {
            String name;
            String data;
            int nameColumn = cur.getColumnIndex(
                    MediaStore.Images.Media.DISPLAY_NAME);

            int dataColumn = cur.getColumnIndex(
                    MediaStore.Images.Media.DATA);

            do {
                // Get the field values
                name = cur.getString(nameColumn);
                data = cur.getString(dataColumn);

                // Do something with the values.
                Log.i("ListingImages", " name=" + name
                        + "  data=" + data);

                Image image = new Image();
                image.setName(name);
                image.setPath(data);

                images.add(image);
            } while (cur.moveToNext());

        }

        return images;
    }
}
