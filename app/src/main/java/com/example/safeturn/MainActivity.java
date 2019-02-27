package com.example.safeturn;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private ViewsContainer container;
    private TextView height;
    private EditText name;
    private EditText rating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate" + savedInstanceState);

        container = findViewById(R.id.container);
        height = findViewById(R.id.height);
        name = findViewById(R.id.editText);
        rating = findViewById(R.id.editText2);
        rating.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(1, 1)});


        findViewById(R.id.button).setOnClickListener((View v) -> {
            try {
                String nameStr = name.getText().toString();
                Double ratingStr = Double.parseDouble(rating.getText().toString());
                ParamsStruct param = new ParamsStruct(nameStr, ratingStr);
                container.incrementViews(param);
                container.getViewTreeObserver().addOnGlobalLayoutListener(LayautListener);

            } catch (NumberFormatException e) {
                Toast.makeText(MainActivity.this, "Enter argument", Toast.LENGTH_SHORT).show();
            }

        });

        container.getViewTreeObserver().addOnGlobalLayoutListener(LayautListener);
    }

    private ViewTreeObserver.OnGlobalLayoutListener LayautListener = new ViewTreeObserver.OnGlobalLayoutListener() {
        @SuppressLint("DefaultLocale")
        @Override
        public void onGlobalLayout() {
            Log.d(TAG, "OnGlobalLayout");
            height.setText(String.format("Высота: %d", container.getHeight()));
            container.getViewTreeObserver().removeOnGlobalLayoutListener(this);
        }
    };

    // Позволяет прятать клавиатуру при нажатии на пустую часть поля
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (v instanceof EditText) {
                Rect outRect = new Rect();
                v.getGlobalVisibleRect(outRect);
                if (!outRect.contains((int) event.getRawX(), (int) event.getRawY())) {
                    v.clearFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }

            }
        }
        return super.dispatchTouchEvent(event);
    }

    @Override
    protected void onResume() {
        Log.d(TAG, "onResume");
        super.onResume();
    }

    @Override
    protected void onStart() {
        Log.d(TAG, "onStart");
        super.onStart();
    }

    @Override
    protected void onPause() {
        Log.d(TAG, "onPause");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.d(TAG, "onStop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "onDestroy");
        super.onDestroy();
    }
}
