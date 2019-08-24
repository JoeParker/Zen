package com.joeparker.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    final Map<Button, SoundButton> soundMap = new HashMap<>();
    int[] rawSounds;

    public static final int MAX_SOUNDS = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Add sounds here
        soundMap.put((Button)findViewById(R.id.spring_birds), new SoundButton(
                R.raw.spring_birds,
                getResources().getDrawable(R.drawable.spring_birds_g),
                getResources().getDrawable(R.drawable.spring_birds)));
        soundMap.put((Button)findViewById(R.id.babbling_brook), new SoundButton(
                R.raw.babbling_brook,
                getResources().getDrawable(R.drawable.babbling_brook_g),
                getResources().getDrawable(R.drawable.babbling_brook)));
        soundMap.put((Button)findViewById(R.id.thunder), new SoundButton(
                R.raw.thunder,
                getResources().getDrawable(R.drawable.thunder_g),
                getResources().getDrawable(R.drawable.thunder)));
        soundMap.put((Button)findViewById(R.id.mockingbird), new SoundButton(
                R.raw.mockingbird,
                getResources().getDrawable(R.drawable.mockingbird_g),
                getResources().getDrawable(R.drawable.mockingbird)));
        soundMap.put((Button)findViewById(R.id.rain), new SoundButton(
                R.raw.rain,
                getResources().getDrawable(R.drawable.rain_g),
                getResources().getDrawable(R.drawable.rain)));
        soundMap.put((Button)findViewById(R.id.dog), new SoundButton(
                R.raw.dog,
                getResources().getDrawable(R.drawable.dog_g),
                getResources().getDrawable(R.drawable.dog)));
        soundMap.put((Button)findViewById(R.id.beach), new SoundButton(
                R.raw.beach,
                getResources().getDrawable(R.drawable.beach_g),
                getResources().getDrawable(R.drawable.beach)));
        soundMap.put((Button)findViewById(R.id.savannah), new SoundButton(
                R.raw.savannah,
                getResources().getDrawable(R.drawable.savannah_g),
                getResources().getDrawable(R.drawable.savannah)));
        soundMap.put((Button)findViewById(R.id.rowing), new SoundButton(
                R.raw.rowing,
                getResources().getDrawable(R.drawable.rowing_g),
                getResources().getDrawable(R.drawable.rowing)));
        soundMap.put((Button)findViewById(R.id.traffic), new SoundButton(
                R.raw.traffic,
                getResources().getDrawable(R.drawable.traffic_g),
                getResources().getDrawable(R.drawable.traffic)));
        soundMap.put((Button)findViewById(R.id.rain_umbrella), new SoundButton(
                R.raw.rain_umbrella,
                getResources().getDrawable(R.drawable.rain_umbrella_g),
                getResources().getDrawable(R.drawable.rain_umbrella)));
        soundMap.put((Button)findViewById(R.id.night), new SoundButton(
                R.raw.night,
                getResources().getDrawable(R.drawable.night_g),
                getResources().getDrawable(R.drawable.night)));

        //Add raw sounds to array and initialise SoundPool manager
        rawSounds = new int[soundMap.size()];
        int index = 0;
        for (SoundButton sb : soundMap.values()) {
            rawSounds[index] = sb.getRawSound();
            index++;
        }
        final SoundManager soundManager = new SoundManager(MAX_SOUNDS, rawSounds, this);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SoundButton soundButton = soundMap.get(view); //get the sound button clicked
                int index = -1; //Error value
                for (int i = 0; i < rawSounds.length; i++) { //get sound index
                    if (rawSounds[i] == soundButton.getRawSound()) {
                        index = i;
                        break;
                    }
                }
                if (index == -1) {
                    System.err.println("Error fetching sound index.");
                }
                //Toggle sound on/off
                if (soundManager.isPlaying(index)) {
                    soundManager.pause(index);
                    view.setBackground(soundButton.getImageOff());
                }
                else {
                    soundManager.playLoop(index);
                    view.setBackground(soundButton.getImageOn());
                }
            }
        };

        for(Button b : soundMap.keySet()) {
            b.setOnClickListener(listener);
        }

    }
}
