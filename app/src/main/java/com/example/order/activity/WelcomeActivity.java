package com.example.order.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.order.R;

public class WelcomeActivity extends AppCompatActivity {
    private static final long SPLASH_DURATION_MS = 2400;
    private final Handler handler = new Handler(Looper.getMainLooper());
    private final Runnable openHome = () -> {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        handler.removeCallbacks(openHome);
        if (hasFocus && !isFinishing()) {
            handler.postDelayed(openHome, SPLASH_DURATION_MS);
        }
    }

    @Override
    protected void onStop() {
        handler.removeCallbacks(openHome);
        super.onStop();
    }
}
