package com.example.safeturn;

import android.content.Context;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ViewsContainer extends LinearLayout {

    private int viewsCount;

    public ViewsContainer(Context context) {
        super(context);
    }

    public ViewsContainer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ViewsContainer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void incrementViews() {
        TextView textView = new TextView(getContext());
        textView.setText(String.valueOf(viewsCount++));
        addView(textView);
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        Parcelable superState = super.onSaveInstanceState();
        return new SavedState(superState, viewsCount);
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        if (!(state instanceof SavedState)) {
            super.onRestoreInstanceState(state);
            return;
        }
        SavedState ss = (SavedState)state;
        super.onRestoreInstanceState(ss.getSuperState());

        for (int i = 0; i < ss.viewsCount; i++)
            incrementViews();
    }

}