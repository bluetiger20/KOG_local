package com.example.kminsu_pc_w1.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends Activity {
//    public static final int REQUEST_CODE_RINGTONE = 10005;



    private static final String TAG = "MainActivity";
    private TextView _text;
    private CountDownTimer _timer;

    private TimerTask mTask;
    private Timer mTimer;
    protected int i=0,minute=0,temp;
    boolean a=false;

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timer_activity_main);

        a = Preference.getBoolean(MainActivity.this, "Mflag");
        if(a!=true) {
            Preference.putBoolean(MainActivity.this, "Mflag", true);
            DBContactHelper helper = new DBContactHelper(this);
            Contact contact = new Contact(0,0,0);
            helper.addContact(contact);
            Contact contact2 = new Contact(1,10,0);
            helper.addContact(contact);
            contact=helper.getContact(1);
            temp=contact.gethour();
            Log.i(TAG, "DB : "+ contact.gethour()+" : "+ contact.getminute());
        }
        _text = (TextView) findViewById(R.id.tvMsg);

        final ToggleButton mtoggle = (ToggleButton) findViewById(R.id.toggleButton2);
        mtoggle.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View arg0) {

                if (mtoggle.isChecked()) {

                    if(timer == null) {
                    TimerTask adTast = new TimerTask() {
                      public void run() {
                        mHandler.sendEmptyMessage(0);
                       }
                  };

                        timer = new Timer();
                        timer.schedule(adTast, 0, 1000); // 0초후 첫실행, 20초마다 계속실행
                        Log.i(TAG, "타이머 시작");
                    }
                } else {
                    if(timer != null) {
                    timer.cancel();
                    Log.i(TAG, "타이머 스탑");
                        timer = null;
                    }
                }

            }
        });

        Button go_to_alram = (Button) findViewById(R.id.button1);
        go_to_alram.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                //@preference로 flag 설정
                Preference.putBoolean(MainActivity.this,"Mflag", true);


                //@preference를 불러와서 flag 확인후 set이 안되있으면 set으로 함

                    Intent intent = new Intent(MainActivity.this, alram_list.class);
                    //Intent intent = new Intent(MainActivity.this, Alarm_main.class);
                    startActivity(intent);


            }
        });
        Button ring = (Button) findViewById(R.id.ringring);
        ring.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Klaxon.class);
                //Intent intent = new Intent(MainActivity.this, Alarm_main.class);
                startActivity(intent);
            }
        });
    }


    Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {

            if(i==1) {
                //@preference flag가 set되어 있으면
                //@현재 시간-preference호출
                //@preference flag가 set되어 있지 않으면
                //@현재 시간을 preference로 저장
                //_text.setText("value = " + minute);
                _text.setText("value = " + temp+i);
                //_text.setText("value = " + timediff(MainActivity.this));
                minute++;
                i=0;
            }
            else{
                i++;
            }


        }
    };


    private static long timediff(Context context) {

        DBContactHelper helper = new DBContactHelper(context);
        Contact contact=helper.getContact(1);
        int hour=contact.gethour();
        int min=contact.getminute();
        /** The date at the end of the last century */

        Date d1 = new GregorianCalendar(Calendar.YEAR,Calendar.MONTH,Calendar.DAY_OF_MONTH, hour, min).getTime();

        /** Today's date */
        Date today = new Date();

        // Get msec from each, and subtract.
        long diff = today.getTime() - d1.getTime();
        return  (diff / (1000 * 60 * 60 ));
    }

    Timer timer;
}