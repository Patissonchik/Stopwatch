package com.example.timer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
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
        setSeconds(0);

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

    }
    public void onClickStop(View view){
        timer.cancel();
    }
    public void onClickReset(View view){
        timer.cancel();
        setSeconds(0);
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
    protected void onStop() {
        super.onStop();
        timer.cancel();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(seconds!=0){
            timer.start();
        }
    }
}


