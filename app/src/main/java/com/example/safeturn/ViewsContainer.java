package com.example.safeturn;

import android.content.Context;
import android.content.res.Resources;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class ViewsContainer extends LinearLayout {

    public ArrayList<ParamsStruct> paramsList;

    public ViewsContainer(Context context) {
        super(context);
    }

    public ViewsContainer(Context context, AttributeSet attrs) {
        super(context, attrs);
        paramsList = new ArrayList<>();
    }

    public ViewsContainer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //paramsList = new ArrayList<>();
    }

    public void incrementViews(ParamsStruct param) {
        paramsList.add(param);
        removeAllViews();

        for (ParamsStruct struct : paramsList) {
            LinearLayout linearLayout = new LinearLayout(getContext());
            linearLayout.setOrientation(LinearLayout.HORIZONTAL);

            LinearLayout.LayoutParams paramsLayout = new LinearLayout.LayoutParams(
                    LayoutParams.WRAP_CONTENT,
                    LayoutParams.WRAP_CONTENT
            );
            paramsLayout.setMargins(dpToPx(10), dpToPx(10), dpToPx(10), 0);

            linearLayout.setLayoutParams(paramsLayout);

            {
                TextView name = new TextView(this.getContext());
                LayoutParams paramTXT = new LayoutParams(dpToPx(120), paramsLayout.WRAP_CONTENT); //Сделать относительным?
                paramTXT.setMargins(dpToPx(10), dpToPx(10), dpToPx(10), 0);
                name.setText(struct.name);
                name.setLayoutParams(paramTXT);
                linearLayout.addView(name);
            }

            {
                View rect = new View(this.getContext());
                int width = (int) Math.round(struct.rating * 10);
                LayoutParams params = new LayoutParams(dpToPx(width), dpToPx(10));
                params.setMargins(0, dpToPx(15), dpToPx(100 - width + 20), 0);
                rect.setLayoutParams(params);
                if (struct.rating == findMaxValue()) {
                    rect.setBackgroundColor(getResources().getColor(R.color.colorRed));
                } else {
                    rect.setBackgroundColor(getResources().getColor(R.color.colorBlack));
                }
                linearLayout.addView(rect);
            }

            {
                TextView rating = new TextView(this.getContext());
                rating.setText(String.valueOf(struct.rating));
                rating.setLayoutParams(paramsLayout);
                linearLayout.addView(rating);
            }

            addView(linearLayout);
        }
    }

    public static int dpToPx(double dp) {
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        double px = dp * (metrics.densityDpi / 160f);
        return (int) Math.round(px);
    }

    public double findMaxValue() {
        double max = Double.MIN_VALUE;
        for (ParamsStruct struct : paramsList) {
            if (struct.rating > max) max = struct.rating;
        }
        return max;
    }

    @Nullable
    @Override
    protected Parcelable onSaveInstanceState() {
        Parcelable superState = super.onSaveInstanceState();
        return new SavedState(superState, paramsList);
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        if (!(state instanceof SavedState)) {
            super.onRestoreInstanceState(state);
            return;
        }
        SavedState ss = (SavedState) state;
        super.onRestoreInstanceState(ss.getSuperState());

        for (ParamsStruct param : ss.paramsList)
            incrementViews(param);
    }

}