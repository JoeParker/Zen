package com.joeparker.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;
    private Button button6;
    private Button button7;
    private Button button8;
    private Button button9;
    private Button button10;
    private Button button11;
    private Button button12;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = (Button)findViewById(R.id.spring_birds);
        final MediaPlayer player1 = MediaPlayer.create(this, R.raw.spring_birds);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (player1.isPlaying()) {
                    player1.pause();
                    button1.setBackground(getResources().getDrawable(R.drawable.spring_birds_g));
                }
                else {
                    player1.start();
                    player1.setLooping(true);
                    button1.setBackground(getResources().getDrawable(R.drawable.spring_birds));
                }
            }
        });

        button2 = (Button)findViewById(R.id.babbling_brook);
        final MediaPlayer player2 = MediaPlayer.create(this, R.raw.babbling_brook);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (player2.isPlaying()) {
                    player2.pause();
                    button2.setBackground(getResources().getDrawable(R.drawable.babbling_brook_g));
                }
                else {
                    player2.start();
                    player2.setLooping(true);
                    button2.setBackground(getResources().getDrawable(R.drawable.babbling_brook));
                }
            }
        });

        button3 = (Button)findViewById(R.id.thunder);
        final MediaPlayer player3 = MediaPlayer.create(this, R.raw.thunder);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (player3.isPlaying()) {
                    player3.pause();
                    button3.setBackground(getResources().getDrawable(R.drawable.thunder_g));
                }
                else {
                    player3.start();
                    player3.setLooping(true);
                    button3.setBackground(getResources().getDrawable(R.drawable.thunder));
                }
            }
        });

        button4 = (Button)findViewById(R.id.mockingbird);
        final MediaPlayer player4 = MediaPlayer.create(this, R.raw.mockingbird);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (player4.isPlaying()) {
                    player4.pause();
                    button4.setBackground(getResources().getDrawable(R.drawable.mockingbird_g));
                }
                else {
                    player4.start();
                    player4.setLooping(true);
                    button4.setBackground(getResources().getDrawable(R.drawable.mockingbird));
                }
            }
        });

        button5 = (Button)findViewById(R.id.rain);
        final MediaPlayer player5 = MediaPlayer.create(this, R.raw.rain);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (player5.isPlaying()) {
                    player5.pause();
                    button5.setBackground(getResources().getDrawable(R.drawable.rain_g));
                }
                else {
                    player5.start();
                    player5.setLooping(true);
                    button5.setBackground(getResources().getDrawable(R.drawable.rain));
                }
            }
        });

        button6 = (Button)findViewById(R.id.dog);
        final MediaPlayer player6 = MediaPlayer.create(this, R.raw.dog);
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (player6.isPlaying()) {
                    player6.pause();
                    button6.setBackground(getResources().getDrawable(R.drawable.dog_g));
                }
                else {
                    player6.start();
                    player6.setLooping(true);
                    button6.setBackground(getResources().getDrawable(R.drawable.dog));
                }
            }
        });

        button7 = (Button)findViewById(R.id.beach);
        final MediaPlayer player7 = MediaPlayer.create(this, R.raw.beach);
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (player7.isPlaying()) {
                    player7.pause();
                    button7.setBackground(getResources().getDrawable(R.drawable.beach_g));
                }
                else {
                    player7.start();
                    player7.setLooping(true);
                    button7.setBackground(getResources().getDrawable(R.drawable.beach));
                }
            }
        });

        button8 = (Button)findViewById(R.id.savannah);
        final MediaPlayer player8 = MediaPlayer.create(this, R.raw.savannah);
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (player8.isPlaying()) {
                    player8.pause();
                    button8.setBackground(getResources().getDrawable(R.drawable.savannah_g));
                }
                else {
                    player8.start();
                    player8.setLooping(true);
                    button8.setBackground(getResources().getDrawable(R.drawable.savannah));
                }
            }
        });

        button9 = (Button)findViewById(R.id.rowing);
        final MediaPlayer player9 = MediaPlayer.create(this, R.raw.rowing);
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (player9.isPlaying()) {
                    player9.pause();
                    button9.setBackground(getResources().getDrawable(R.drawable.rowing_g));
                }
                else {
                    player9.start();
                    player9.setLooping(true);
                    button9.setBackground(getResources().getDrawable(R.drawable.rowing));
                }
            }
        });

        button10 = (Button)findViewById(R.id.traffic);
        final MediaPlayer player10 = MediaPlayer.create(this, R.raw.traffic);
        button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (player10.isPlaying()) {
                    player10.pause();
                    button10.setBackground(getResources().getDrawable(R.drawable.traffic_g));
                }
                else {
                    player10.start();
                    player10.setLooping(true);
                    button10.setBackground(getResources().getDrawable(R.drawable.traffic));
                }
            }
        });

        button11 = (Button)findViewById(R.id.rain_umbrella);
        final MediaPlayer player11 = MediaPlayer.create(this, R.raw.rain_umbrella);
        button11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (player11.isPlaying()) {
                    player11.pause();
                    button11.setBackground(getResources().getDrawable(R.drawable.rain_umbrella_g));
                }
                else {
                    player11.start();
                    player11.setLooping(true);
                    button11.setBackground(getResources().getDrawable(R.drawable.rain_umbrella));
                }
            }
        });

        button12 = (Button)findViewById(R.id.night);
        final MediaPlayer player12 = MediaPlayer.create(this, R.raw.night);
        button12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (player12.isPlaying()) {
                    player12.pause();
                    button12.setBackground(getResources().getDrawable(R.drawable.night_g));
                }
                else {
                    player12.start();
                    player12.setLooping(true);
                    button12.setBackground(getResources().getDrawable(R.drawable.night));
                }
            }
        });

    }
}
