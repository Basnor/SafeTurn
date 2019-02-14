package com.example.safeturn;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private LinearLayout container;
    private int viewsCount = 0;
    private static final String STATE_VIEWS_COUNT = "viewsCount";
    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate" + savedInstanceState);


        container = findViewById(R.id.container);

        if (savedInstanceState != null) {
            int count = savedInstanceState.getInt(STATE_VIEWS_COUNT);
            for (int i = 0; i < count; i++) {
                addTextView();
            }
        }

        findViewById(R.id.button).setOnClickListener((View v) -> {
            addTextView();
        });

    }

    private void addTextView() {
        TextView textView = new TextView(MainActivity.this);
        textView.setText(String.valueOf(viewsCount++));
        container.addView(textView);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState");
        outState.putInt(STATE_VIEWS_COUNT, viewsCount);
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
