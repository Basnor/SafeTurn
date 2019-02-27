package com.example.safeturn;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

public class ParamsStruct implements Parcelable {
    private static final String TAG = "INPUT PARAMS";

    public String name;
    public double rating;

    // constructor
    public ParamsStruct(String name, double rating) {
        this.name = name;
        this.rating = rating;
    }

    private ParamsStruct(Parcel source) {
        name = source.readString();
        rating = source.readDouble();
        Log.d(TAG, "GET param: " + name + "  " + rating);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int i) {
        out.writeString(name);
        out.writeDouble(rating);
    }

    public static final Parcelable.Creator<ParamsStruct> CREATOR =
            new Parcelable.Creator<ParamsStruct>() {
                public ParamsStruct createFromParcel(Parcel source) {
                    return new ParamsStruct(source);
                }

                public ParamsStruct[] newArray(int size) {
                    return new ParamsStruct[size];
                }
            };
}
