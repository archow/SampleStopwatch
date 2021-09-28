package com.example.samplestopwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private TextView mCurrentTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mCurrentTime = findViewById(R.id.current_time_tv);

        displayTime();

        Thread myThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while(!Thread.currentThread().isInterrupted()){
                    try {
                        updateTime();
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }catch(Exception e){
                    }
                }
            }
        });
        myThread.start();


    }

    private void displayTime() {
        Date dt = new Date();
        int hours = dt.getHours();
        int minutes = dt.getMinutes();
        int seconds = dt.getSeconds();
        String curTime = hours + ":" + minutes + ":" + seconds;
        mCurrentTime.setText(curTime);
    }

    private void updateTime() {
        runOnUiThread(new Runnable() {
            public void run() {
                try{
                    displayTime();
                }catch (Exception e) {}
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //if thread is not null, cancel the thread
    }
}
