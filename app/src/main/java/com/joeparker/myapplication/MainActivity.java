package com.joeparker.myapplication;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

     Menu menu;
     View view;

    final Map<Button, SoundButton> soundMap = new HashMap<>();
    final List<SoundButton> currentlyPlaying = new ArrayList<>();

    final Map<String, List<SoundButton>> presets = new HashMap<>();

    boolean paused = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.view = findViewById(android.R.id.content);

        //Add sounds here
        soundMap.put((Button)findViewById(R.id.spring_birds), new SoundButton(  //Button ID
                this, R.raw.spring_birds,                                //Sound file
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
                    currentlyPlaying.remove(soundButton);
                }
                else {
                    for (SoundButton sb : soundMap.values()) {
                        if (sb.getPlayer().isPlaying()) {
                            paused = false;
                            break;
                        }
                    }
                    if (paused) {
                        currentlyPlaying.clear();
                        menu.findItem(R.id.pause).setIcon(android.R.drawable.ic_media_pause);
                    }
                    player.start();
                    player.setLooping(true);
                    view.setBackground(soundButton.getImageOn());
                    currentlyPlaying.add(soundButton);
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
        this.menu = menu;

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.save:
                save(this, currentlyPlaying, presets);
                return true;
            case R.id.pause:
                if (paused) {
                    if(!currentlyPlaying.isEmpty()) {
                        resume(soundMap, currentlyPlaying);
                        item.setIcon(android.R.drawable.ic_media_pause);
                        paused = false;
                    }
                }
                else {
                    pause(soundMap, currentlyPlaying);
                    item.setIcon(android.R.drawable.ic_media_play);
                    paused = true;
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //Menu button functions
    private static void save(final Context context, final List<SoundButton> currentlyPlaying, final Map<String, List<SoundButton>> presets){
        final EditText textInput = new EditText(context);
        textInput.setHint("");
        new AlertDialog.Builder(context, R.style.AlertTheme)
                .setTitle("Save Preset")
                .setMessage("Name your preset:")
                .setView(textInput)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int whichButton) {
                        String name = textInput.getText().toString();
                        if (name.isEmpty()) {
                            Toast.makeText(context, "Please enter a valid name", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            presets.put(name, currentlyPlaying);
                            Toast.makeText(context, "Preset saved", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }
                    }
                })
                .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int whichButton) {
                        dialog.cancel();;
                    }
                })
                .show();
    }

    //Stop all currently playing sounds
    private static void pause(Map<Button, SoundButton> soundMap, List<SoundButton> currentlyPlaying) {
        for (SoundButton sb : currentlyPlaying) {
            sb.getPlayer().pause();
            try {
                Button button = (Button) Helper.getKeyFromValue(soundMap, sb);
                button.setBackground(sb.getImageOff());
            } catch (NullPointerException npe) {
                System.err.println("Error pausing sound, no matching button was found for the sound.");
            }
        }
    }

    //Resume most recently played sounds
    private static void resume(Map<Button, SoundButton> soundMap, List<SoundButton> currentlyPlaying) {
        for (SoundButton sb : currentlyPlaying) {
            sb.getPlayer().start();
            sb.getPlayer().setLooping(true);
            try {
                Button button = (Button) Helper.getKeyFromValue(soundMap, sb);
                button.setBackground(sb.getImageOn());
            } catch (NullPointerException npe) {
                System.err.println("Error resuming sound, no matching button was found for the sound.");
            }
        }
    }
}
