package com.example.safeturn;

import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;

import java.util.List;

public class SavedState extends View.BaseSavedState {
    public List<ParamsStruct> paramsList;

    SavedState(Parcelable superState) {
        super(superState);
    }

    // Конструктор вызывается при восстановлении состояния
    private SavedState(Parcel source) {
        super(source);
        paramsList = source.createTypedArrayList(ParamsStruct.CREATOR);
    }

    // Конструктор вызывается при сохранении состояния
    public SavedState(Parcelable superState, List<ParamsStruct> paramsList) {
        super(superState);
        this.paramsList = paramsList;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        super.writeToParcel(out, flags);
        out.writeTypedList(paramsList);
    }
}
