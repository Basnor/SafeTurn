package com.example.safeturn;

import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;

public class SavedState extends View.BaseSavedState {
    public final int viewsCount;

    // Конструктор вызывается при восстановлении состояния
    public SavedState(Parcel source) {
        super(source);
        viewsCount = source.readInt();
    }

    // Конструктор вызывается при сохранении состояния
    public SavedState(Parcelable superState, int viewsCount) {
        super(superState);
        this.viewsCount = viewsCount;
    }

    @Override
    public void writeToParcel(Parcel out, int flags){
        super.writeToParcel(out, flags);
        out.writeInt(viewsCount);
    }

}
