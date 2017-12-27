package com.example.umar.intentservice;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import static android.media.session.PlaybackState.ACTION_STOP;

public class MainActivity extends AppCompatActivity {
    Button btnStart,btnStop;
    private static final String TAG = "MTAG";
    TextView tvShow;
    int i=10;

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EventBus.getDefault().register(this);
        btnStart = findViewById(R.id.btnStart);
        tvShow = findViewById(R.id.tvShow);
        btnStop=findViewById(R.id.btnStop);
        Log.d(TAG, "On create" );

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG,  "start button" );
                final Intent intent = new Intent(MainActivity.this,MyIntentService.class);
                startService(intent);
                Log.d(TAG, "intent start" );
            }
        });


        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MyIntentService.class);
              stopService(intent);

            }
        });


    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {
        Integer a = event.getMessage();
        tvShow.setText(a.toString());
    }
}
