package com.example.kminsu_pc_w1.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

    private static final String TAG = "MainActivity";

    private TextView _text;
    private CountDownTimer _timer;

    private Button btnTest;

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timer_activity_main);

        _text = (TextView)findViewById(R.id.tvMsg);

        _timer = new CountDownTimer(10 * 1000, 1000) {
            public void onTick(long millisUntilFinished) {
                _text.setText("value = " + millisUntilFinished);
                Log.i(TAG, "타이머 ㅋㅋ");
            }

            public void onFinish() {
                _text.setText("finshed");
            }

        };

        Button btnStart = (Button)findViewById(R.id.btnStart);
        btnStart.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                _timer.start();
                Log.i(TAG, "타이머 시작");
            }
        });

        Button btnEnd = (Button)findViewById(R.id.btnStop);
        btnEnd.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                _timer.cancel();
                Log.i(TAG, "타이머 스탑");
            }
        });

        Button go_to_alram = (Button)findViewById(R.id.button1);
        go_to_alram.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, alram_list.class);
                //Intent intent = new Intent(MainActivity.this, Alarm_main.class);
                startActivity(intent);
            }
        });





    }


}