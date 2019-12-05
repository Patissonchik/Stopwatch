package com.example.timer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private int seconds;
    public void setSeconds(int value){
        this.seconds = value;
        updateTimeView();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int seconds = (savedInstanceState != null)? savedInstanceState.getInt("seconds"):0;
        setSeconds(seconds);
        if(seconds!= 0){
            timer.start();
            Button button =(Button)findViewById(R.id.button);
            button.setEnabled(false);
        }
}
    private CountDownTimer timer = new CountDownTimer(Long.MAX_VALUE, 1000) {

        public void onTick(long millisUntilFinished) {
            setSeconds(++seconds);
        }

        public void onFinish() {
        }
    };

    public void onClickStart (View view){
        timer.start();
        Button button =(Button)findViewById(R.id.button);
        button.setEnabled(false);

    }
    public void onClickStop(View view){
        timer.cancel();
        Button button =(Button)findViewById(R.id.button);
        button.setEnabled(true);
    }
    public void onClickReset(View view){
        timer.cancel();
        setSeconds(0);
        Button button =(Button)findViewById(R.id.button);
        button.setEnabled(true);
    }
    public void updateTimeView() {
        int ss = seconds % 60;
        int minutes = seconds / 60;
        int mm = minutes % 60;
        int hours = minutes / 60;
        String timeString = String.format("%02d:%02d:%02d", hours, mm, ss);

        TextView timeView = (TextView) findViewById(R.id.time);
        timeView.setText(timeString);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("seconds", seconds);
    }
}


