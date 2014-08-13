package com.example.kminsu_pc_w1.myapplication;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.Menu;
import android.view.MenuItem;
import com.example.kminsu_pc_w1.myapplication.R;
import android.content.Context;
import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;


public class Klaxon extends Activity {
//    private Vibrator mVibrator;
//    private static final long[] sVibratePattern = new long[] { 500, 500 };   // 진동 패턴 정의(0.5초 진동, 0.5초 쉼)
    private MediaPlayer mMediaPlayer;   // MediaPlayer 변수 선언
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_klaxon);
//        vibrate_function();
        playMusic();
    }
        private void playMusic() {
            stopMusic();  // 플레이 할 때 가장 먼저 음악 중지 실행
            Uri alert = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);  // 기본 벨소리(알람)의 URI
            mMediaPlayer = new MediaPlayer();  // 1. MediaPlayer 객체 생성

            try {
                //mMediaPlayer.setDataSource();
                mMediaPlayer.setDataSource(null, alert);  // 2. 데이터 소스 설정 (인터넷에 있는 음악 파일도 가능함)
                startAlarm(mMediaPlayer);
            } catch (Exception ex) {
                try {
                    mMediaPlayer.reset();    // MediaPlayer의 Error 상태 초기화
                   // setDataSourceFromResource(getResources(), mMediaPlayer, R.raw.fallbackring); // fallbackring.ogg 사용
                    startAlarm(mMediaPlayer);
                } catch (Exception ex2) {
                    ex2.printStackTrace();
                }
            }
        }
//    private void vibrate_function(){
//        mVibrator =  (Vibrator)getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
//        mVibrator.vibrate(sVibratePattern, 0);   // 진동 시작 (패턴으로 진동, '0':무한 반복, -1:반복 없음)
//
////        mVibrator.cancel();   // 진동 중지
//    }
        public void startAlarm(MediaPlayer player) throws java.io.IOException, IllegalArgumentException, IllegalStateException {
            final AudioManager audioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
            if (audioManager.getStreamVolume(AudioManager.STREAM_ALARM) != 0) {   // 현재 Alarm 볼륨 구함
                player.setAudioStreamType(AudioManager.STREAM_ALARM);    // Alarm 볼륨 설정
                player.setLooping(true);    // 음악 반복 재생
                player.prepare();   // 3. 재생 준비
                player.start();    // 4. 재생 시작
            }
        }

        public void stopMusic() {
            if (mMediaPlayer != null) {
                mMediaPlayer.stop();     // 5. 재생 중지
                mMediaPlayer.release();    // 6. MediaPlayer 리소스 해제
                mMediaPlayer = null;
            }
        }

        public void onDestroy() {
            // super.onDestroy();
            stopMusic();    // 음악 중지.
        }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.klaxon, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
