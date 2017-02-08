package com.example.future.mongiz;
import android.app.Activity;
import android.app.KeyguardManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class MainActivity extends Activity {
       Button button;
       EditText editText;
       TextView textView, view, time_lift;
       long time, time2;
       private static final String FORMAT = "%02d:%02d:%02d";
       Handler handler;
       int[]resID;
       MediaPlayer mediaPlayer;
       int[] sound;
       int high , low , rnd;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.button);
        textView = (TextView) findViewById(R.id.textView2);
        editText = (EditText) findViewById(R.id.editText);
        view = (TextView) findViewById(R.id.textView3);
        time_lift = (TextView) findViewById(R.id.textView4);
        button.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (editText.getText().toString().trim().equals("")) {
                    Toast.makeText(MainActivity.this, "please enter time", Toast.LENGTH_LONG).show();
                } else {
                    time = Integer.parseInt(editText.getText().toString().trim());
                    time2 = time * 1000 * 60;
                    handler = new Handler();
                    Runnable r = new Runnable() {
                        @Override
                        public void run() {
                            time2 = time2 - 1000;
                            if (time2 > 0) {
                                handler.postDelayed(this, 1000);
                                time_lift.setText("" + String.format(FORMAT,
                                        TimeUnit.MILLISECONDS.toHours(time2),
                                        TimeUnit.MILLISECONDS.toMinutes(time2) - TimeUnit.HOURS.toMinutes(
                                                TimeUnit.MILLISECONDS.toHours(time2)),
                                        TimeUnit.MILLISECONDS.toSeconds(time2) - TimeUnit.MINUTES.toSeconds(
                                                TimeUnit.MILLISECONDS.toMinutes(time2))));
                                bringApplicationToFront();
                                editText.setVisibility(View.GONE);
                                button.setVisibility(View.GONE);
                                Log.e("run: ", Long.toString(time2));
                            } else {
                                Finish();
                            }
                        }
                    };
                    handler.postDelayed(r, 1000);
                }
            }
        });
    }
    public void playsound() {
        rand_om();
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = MediaPlayer.create(this, sound[rnd]);
        }
        mediaPlayer.start();
    }
    public void  rand_om(){
      sound = new int[]{R.raw.alamal, R.raw.elkhof, R.raw.ensan, R.raw.enwanak, R.raw.matkhaleeh, R.raw.mewaafak, R.raw.tawakol};
        Random random = new Random();
        high = 7;
         low = 0;
         rnd = random.nextInt(high- low) + low;
         mediaPlayer =MediaPlayer.create(this, sound[rnd]);
    }
    public void Finish() {
        editText.setText("");
        editText.setVisibility(View.VISIBLE);
        button.setVisibility(View.VISIBLE);
        playsound();
        time_lift.setText(" استمع! لن يستمر الملف أكثر من دقيقة ");
    }

    private void bringApplicationToFront() {
        KeyguardManager myKeyManager = (KeyguardManager) getSystemService(Context.KEYGUARD_SERVICE);
        if (myKeyManager.inKeyguardRestrictedInputMode())
            return;
        Log.d("TAG", "====Bringging Application to Front====");
        Intent notificationIntent = new Intent(this, MainActivity.class);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);
        try {
            pendingIntent.send();
        } catch (PendingIntent.CanceledException e) {
            e.printStackTrace();
        }
    }

    public void onBackPressed() {
    }
}