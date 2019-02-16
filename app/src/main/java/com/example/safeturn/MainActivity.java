package com.example.safeturn;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private ViewsContainer container;
    private TextView height;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate" + savedInstanceState);

        container = findViewById(R.id.container);
        height = findViewById(R.id.height);

        findViewById(R.id.button).setOnClickListener((View v) -> {
            container.incrementViews();
            container.getViewTreeObserver().addOnGlobalLayoutListener(LayautListener);
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
