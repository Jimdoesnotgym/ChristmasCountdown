package com.jimdoesnotgym.ms076.halloweencountdown;

import android.graphics.drawable.AnimationDrawable;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Date;

import me.anwarshahriar.calligrapher.Calligrapher;

public class MainActivity extends AppCompatActivity {
    TextView txtDaysRemain, txtLongString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Calligrapher calligrapher = new Calligrapher(this);
        calligrapher.setFont(this, "christmas-font.ttf", true);

        /*ImageView pumpkin = (ImageView)findViewById(R.id.pumpkin);
        ((AnimationDrawable)pumpkin.getDrawable()).start();*/

        txtDaysRemain = (TextView)findViewById(R.id.txtDaysRemain);
        txtLongString = (TextView)findViewById(R.id.txtLongString);

        startTimer();
    }

    private void startTimer() {
        long differance = getRemainDays();
        new CountDownTimer(differance, 1000){
            public void onTick(long millisUntilFinished){
                int days = (int)(millisUntilFinished/(1000*60*60*24));
                int hours = (int)((millisUntilFinished/(1000*60*60))%24);
                int min = (int)((millisUntilFinished/(1000*60))%60);
                int sec = (int)(millisUntilFinished/1000)%24;

                txtDaysRemain.setText(String.format("%d", days));
                txtLongString.setText(String.format("%d DAYS %02d: %02d: %02d", days, hours, min, sec));
            }
            public void onFinish(){
                //Done
            }
        }.start();
    }

    private long getRemainDays() {
        Date currentDate = new Date();
        Date endDate;

        //months in Date are 0-11
        if(currentDate.getMonth() <= 11){
            endDate = new Date(currentDate.getYear(),11, 25);
        }else {
            endDate = new Date(currentDate.getYear()+1, 11, 25); //Next Year
        }
        return endDate.getTime() - currentDate.getTime();
    }
}
