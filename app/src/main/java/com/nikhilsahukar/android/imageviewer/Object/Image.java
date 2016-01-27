package com.nikhilsahukar.android.imageviewer.Object;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Nikhil on 27/01/16.
 */
public class Image implements Parcelable {

    private String mName;
    private String mPath;

    public void setName(String name) {
        mName = name;
    }

    public void setPath(String path) {
        mPath = path;
    }

    public String getName() {
        return mName;
    }

    public String getPath() {
        return mPath;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int i) {
        out.writeString(mName);
        out.writeString(mPath);
    }

    public static final Parcelable.Creator<Image> CREATOR
            = new Parcelable.Creator<Image>() {
        public Image createFromParcel(Parcel in) {
            return new Image(in);
        }

        public Image[] newArray(int size) {
            return new Image[size];
        }
    };

    private Image(Parcel in) {
        mName = in.readString();
        mPath = in.readString();
    }

    public Image() {
        mName = null;
        mPath = null;
    }
}
