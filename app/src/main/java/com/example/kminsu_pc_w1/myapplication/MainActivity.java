package com.example.kminsu_pc_w1.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends Activity {
//    public static final int REQUEST_CODE_RINGTONE = 10005;



    private static final String TAG = "MainActivity";
    private TextView _text;

    protected int i=0,minute=0,diff_hour,diff_min;
    boolean a=false;

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timer_activity_main);

        a = Preference.getBoolean(MainActivity.this, "testValue");
        Log.i(TAG, "a : "+ a);
        if(!a) {
            Preference.putBoolean(MainActivity.this, "testValue", true);
            DBContactHelper helper = new DBContactHelper(this);
            Contact contact = new Contact(0,00,00);
            helper.addContact(contact);
            Contact contact2 = new Contact(1,10,00);
            helper.addContact(contact2);
        }
        _text = (TextView) findViewById(R.id.tvMsg);

        final ToggleButton mtoggle = (ToggleButton) findViewById(R.id.toggleButton2);
        mtoggle.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View arg0) {

                if (mtoggle.isChecked()) {

                    if(timer == null) {
                        Date start = new Date();
                        Preference.putLong(MainActivity.this, "start", start.getTime());

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

                //@preference flag가 set되어 있으면
                //@현재 시간-preference호출
                //@preference flag가 set되어 있지 않으면
                //@현재 시간을 preference로 저장
                _text.setText(timediff(MainActivity.this));



                minute++;



        }
    };


    private String timediff(Context context) {

        DBContactHelper helper = new DBContactHelper(context);
        Contact contact=helper.getContact(2);
        int id=contact.getID();
        int hour=contact.gethour();
        int min=contact.getminute();


        /** The date at the end of the last century */

     //   Date Date1 = new GregorianCalendar(Calendar.YEAR,Calendar.MONTH,Calendar.DAY_OF_MONTH, hour,min).getTime();

        /** Today's date */
        //Date Date2 = new Date();
      /*  Log.i(TAG, "today : "+ today.getTime());
        long diff= Math.abs((today.getTime() - d1.getTime()));
          Date diffdate=new Date(diff);


        Log.i(TAG, "hour : "+ diffdate.toString());

        // Get msec from each, and subtract.
        diff_hour = today.getHours() - d1.getHours();
        diff_min=today.getMinutes()-d1.getMinutes();*/

        try
        {

            SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss aa");


            Date Date1=new Date(Preference.getLong(MainActivity.this, "start"));
            Log.i(TAG, "DAte1 : "+ Date1.toString());
            Date today = new Date();
            Log.i(TAG, "today : "+ today.toString());
            long mills = today.getTime()-Date1.getTime();

            int Hours = (int) (mills/(1000 * 60 * 60));
            int Mins = (int) (mills/(1000*60)) % 60;
            int Seconds = (int) (mills/1000)%60;
            /*
            Preference.putInt(MainActivity.this, "hour", Hours);
            Preference.putInt(MainActivity.this, "min", Mins);
            Preference.putInt(MainActivity.this, "second", Seconds);*/
            String diff = Hours + ":" + Mins+":"+Seconds; // updated value every1 second
            return diff;
         }
        catch (Exception e)
        {
            e.printStackTrace();
        }
      return "error";
    }



    Timer timer;
}