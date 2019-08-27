package com.joeparker.myapplication;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    final Map<Button, SoundButton> soundMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar menu = getSupportActionBar();
        menu.setTitle("Ambience");

        //Add sounds here
        soundMap.put((Button)findViewById(R.id.spring_birds), new SoundButton(  //Button ID
                this, R.raw.spring_birds,                                             //Sound file
                getResources().getDrawable(R.drawable.spring_birds_g),          //Image (greyscale)
                getResources().getDrawable(R.drawable.spring_birds)));          //Image (colour)
        soundMap.put((Button)findViewById(R.id.babbling_brook), new SoundButton(
                this, R.raw.babbling_brook,
                getResources().getDrawable(R.drawable.babbling_brook_g),
                getResources().getDrawable(R.drawable.babbling_brook)));
        soundMap.put((Button)findViewById(R.id.thunder), new SoundButton(
                this, R.raw.thunder,
                getResources().getDrawable(R.drawable.thunder_g),
                getResources().getDrawable(R.drawable.thunder)));
        soundMap.put((Button)findViewById(R.id.mockingbird), new SoundButton(
                this, R.raw.mockingbird,
                getResources().getDrawable(R.drawable.mockingbird_g),
                getResources().getDrawable(R.drawable.mockingbird)));
        soundMap.put((Button)findViewById(R.id.rain), new SoundButton(
                this, R.raw.rain,
                getResources().getDrawable(R.drawable.rain_g),
                getResources().getDrawable(R.drawable.rain)));
        soundMap.put((Button)findViewById(R.id.dog), new SoundButton(
                this, R.raw.dog,
                getResources().getDrawable(R.drawable.dog_g),
                getResources().getDrawable(R.drawable.dog)));
        soundMap.put((Button)findViewById(R.id.beach), new SoundButton(
                this, R.raw.beach,
                getResources().getDrawable(R.drawable.beach_g),
                getResources().getDrawable(R.drawable.beach)));
        soundMap.put((Button)findViewById(R.id.savannah), new SoundButton(
                this, R.raw.savannah,
                getResources().getDrawable(R.drawable.savannah_g),
                getResources().getDrawable(R.drawable.savannah)));
        soundMap.put((Button)findViewById(R.id.rowing), new SoundButton(
                this, R.raw.rowing,
                getResources().getDrawable(R.drawable.rowing_g),
                getResources().getDrawable(R.drawable.rowing)));
        soundMap.put((Button)findViewById(R.id.traffic), new SoundButton(
                this, R.raw.traffic,
                getResources().getDrawable(R.drawable.traffic_g),
                getResources().getDrawable(R.drawable.traffic)));
        soundMap.put((Button)findViewById(R.id.rain_umbrella), new SoundButton(
                this, R.raw.rain_umbrella,
                getResources().getDrawable(R.drawable.rain_umbrella_g),
                getResources().getDrawable(R.drawable.rain_umbrella)));
        soundMap.put((Button)findViewById(R.id.night), new SoundButton(
                this, R.raw.night,
                getResources().getDrawable(R.drawable.night_g),
                getResources().getDrawable(R.drawable.night)));
        soundMap.put((Button)findViewById(R.id.fireplace), new SoundButton(
                this, R.raw.fireplace,
                getResources().getDrawable(R.drawable.fireplace_g),
                getResources().getDrawable(R.drawable.fireplace)));
        soundMap.put((Button)findViewById(R.id.bells), new SoundButton(
                this, R.raw.bells,
                getResources().getDrawable(R.drawable.bells_g),
                getResources().getDrawable(R.drawable.bells)));
        soundMap.put((Button)findViewById(R.id.water_fountain), new SoundButton(
                this, R.raw.water_fountain,
                getResources().getDrawable(R.drawable.water_fountain_g),
                getResources().getDrawable(R.drawable.water_fountain)));
        soundMap.put((Button)findViewById(R.id.windchimes), new SoundButton(
                this, R.raw.windchimes,
                getResources().getDrawable(R.drawable.windchimes_g),
                getResources().getDrawable(R.drawable.windchimes)));
        soundMap.put((Button)findViewById(R.id.bonfire), new SoundButton(
                this, R.raw.bonfire,
                getResources().getDrawable(R.drawable.bonfire_g),
                getResources().getDrawable(R.drawable.bonfire)));

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SoundButton soundButton = soundMap.get(view); //get the sound button clicked
                MediaPlayer player = soundButton.getPlayer();
                if (player.isPlaying()) {
                    player.pause();
                    view.setBackground(soundButton.getImageOff());
                }
                else {
                    player.start();
                    player.setLooping(true);
                    view.setBackground(soundButton.getImageOn());
                }

            }
        };

        for(Button b : soundMap.keySet()) {
            b.setOnClickListener(listener);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.save:
                save(soundMap, this);
                return true;
            case R.id.stop:
                stop(soundMap, this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //Menu button functions
    private static void save(Map m, Context context) {
        List<SoundButton> nowPlaying = new ArrayList<>();
        Collection soundButtons = m.values();
        for (Object s : soundButtons) {
            if (s instanceof SoundButton) {
                SoundButton sb = (SoundButton)s;
                MediaPlayer player = sb.getPlayer();
                if (player.isPlaying()) {
                    nowPlaying.add(sb);
                }
            }
            else {
                System.err.println("Save error, unable to cast SoundButton object.");
            }
        }

        Toast.makeText(context, "Saved selection", Toast.LENGTH_SHORT).show();
    }

    //Stop all currently playing sounds
    private static void stop(Map m, Activity context) {
        List<SoundButton> nowPlaying = new ArrayList<>();
        Collection soundButtons = m.values();
        for (Object s : soundButtons) {
            if (s instanceof SoundButton) {
                SoundButton sb = (SoundButton)s;
                MediaPlayer player = sb.getPlayer();
                if (player.isPlaying()) {
                    nowPlaying.add(sb);
                }
            }
            else {
                System.err.println("Stop error, unable to cast SoundButton object.");
            }
        }
        for (SoundButton sb : nowPlaying) {
            Button button = context.findViewById(R.id.spring_birds);
            sb.getPlayer().pause();
            Set<Map.Entry<Button, SoundButton>> entries = m.entrySet();
            for (Map.Entry entry : entries) {
                if (Objects.equals(sb, entry.getValue())) {
                    button = (Button) entry.getKey();
                }
            }
            button.setBackground(sb.getImageOff());
        }
    }
}
