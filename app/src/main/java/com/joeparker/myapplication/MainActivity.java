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
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
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
    final Map<SoundButton, MediaPlayer> playerMap = new HashMap<>();

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
                R.raw.spring_birds,                                             //Sound file
                R.drawable.spring_birds_g,                                      //Image (greyscale)
                R.drawable.spring_birds));                                      //Image (colour)
        soundMap.put((Button)findViewById(R.id.babbling_brook), new SoundButton(
                R.raw.babbling_brook,
                R.drawable.babbling_brook_g,
                R.drawable.babbling_brook));
        soundMap.put((Button)findViewById(R.id.thunder), new SoundButton(
                R.raw.thunder,
                R.drawable.thunder_g,
                R.drawable.thunder));
        soundMap.put((Button)findViewById(R.id.mockingbird), new SoundButton(
                R.raw.mockingbird,
                R.drawable.mockingbird_g,
                R.drawable.mockingbird));
        soundMap.put((Button)findViewById(R.id.rain), new SoundButton(
                R.raw.rain,
                R.drawable.rain_g,
                R.drawable.rain));
        soundMap.put((Button)findViewById(R.id.dog), new SoundButton(
                R.raw.dog,
                R.drawable.dog_g,
                R.drawable.dog));
        soundMap.put((Button)findViewById(R.id.beach), new SoundButton(
                R.raw.beach,
                R.drawable.beach_g,
                R.drawable.beach));
        soundMap.put((Button)findViewById(R.id.savannah), new SoundButton(
                R.raw.savannah,
                R.drawable.savannah_g,
                R.drawable.savannah));
        soundMap.put((Button)findViewById(R.id.rowing), new SoundButton(
                R.raw.rowing,
                R.drawable.rowing_g,
                R.drawable.rowing));
        soundMap.put((Button)findViewById(R.id.traffic), new SoundButton(
                R.raw.traffic,
                R.drawable.traffic_g,
                R.drawable.traffic));
        soundMap.put((Button)findViewById(R.id.rain_umbrella), new SoundButton(
                R.raw.rain_umbrella,
                R.drawable.rain_umbrella_g,
                R.drawable.rain_umbrella));
        soundMap.put((Button)findViewById(R.id.night), new SoundButton(
                R.raw.night,
                R.drawable.night_g,
                R.drawable.night));
        soundMap.put((Button)findViewById(R.id.fireplace), new SoundButton(
                R.raw.fireplace,
                R.drawable.fireplace_g,
                R.drawable.fireplace));
        soundMap.put((Button)findViewById(R.id.bells), new SoundButton(
                R.raw.bells,
                R.drawable.bells_g,
                R.drawable.bells));
        soundMap.put((Button)findViewById(R.id.water_fountain), new SoundButton(
                R.raw.water_fountain,
                R.drawable.water_fountain_g,
                R.drawable.water_fountain));
        soundMap.put((Button)findViewById(R.id.windchimes), new SoundButton(
                R.raw.windchimes,
                R.drawable.windchimes_g,
                R.drawable.windchimes));
        soundMap.put((Button)findViewById(R.id.bonfire), new SoundButton(
                R.raw.bonfire,
                R.drawable.bonfire_g,
                R.drawable.bonfire));

        for (SoundButton sb : soundMap.values()) {
            playerMap.put(sb, MediaPlayer.create(this, sb.getRawSound()));
        }

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SoundButton soundButton = soundMap.get(view); //get the sound button clicked
                MediaPlayer player = playerMap.get(soundButton);
                if (player.isPlaying()) {
                    player.pause();
                    view.setBackground(getResources().getDrawable(soundButton.getImageOffID()));
                    currentlyPlaying.remove(soundButton);
                }
                else {
                    for (SoundButton sb : soundMap.values()) {
                        if (playerMap.get(sb).isPlaying()) {
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
                    view.setBackground(getResources().getDrawable(soundButton.getImageOnID()));
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
                        resume(this, playerMap, soundMap, currentlyPlaying);
                        item.setIcon(android.R.drawable.ic_media_pause);
                        paused = false;
                    }
                }
                else {
                    pause(this, playerMap, soundMap, currentlyPlaying);
                    item.setIcon(android.R.drawable.ic_media_play);
                    paused = true;
                }
                return true;
            case R.id.presets:
                try {
                    Map<String, List<SoundButton>> storedPresets = (Map<String, List<SoundButton>>)Helper.readObject(this, "Presets");
                    Set<String> names = storedPresets.keySet();


                    //Test load preset in slot 1
                    CharSequence name1 = names.iterator().next();
                    Toast.makeText(this, "Playing preset: " + name1, Toast.LENGTH_SHORT).show();
                    List<SoundButton> preset1 = storedPresets.get(name1);
                    resume(this, playerMap, soundMap, preset1);

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //Menu button functions
    private static void save(final Context context, final List<SoundButton> currentlyPlaying, final Map<String, List<SoundButton>> presets){
        final EditText textInput = new EditText(context);
        textInput.setHint("Please enter a valid name");
        final AlertDialog dialog = new AlertDialog.Builder(context, R.style.AlertTheme)
                .setTitle("Save Preset")
                .setMessage("Name your preset:")
                .setView(textInput)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override //Confirm button clicked
                    public void onClick(DialogInterface dialog, int whichButton) {
                        //Do nothing here as we need to handle validation below
                    }
                })
                .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override //Cancel button clicked
                    public void onClick(DialogInterface dialog, int whichButton) {
                        dialog.cancel();;
                    }
                })
                .show();

                dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
                    @Override //Handle input validation
                    public void onClick(View v) {
                        String name = textInput.getText().toString();
                        if (name.isEmpty()) {
                            Toast.makeText(context, "Please enter a valid name", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            //Save preset to internal storage
                            try {
                                presets.put(name, currentlyPlaying);
                                Helper.writeObject(context, "Presets", presets);
                                Toast.makeText(context, "Preset saved", Toast.LENGTH_SHORT).show();
                            } catch (IOException e) {
                                Toast.makeText(context, "Error saving preset: There may not be enough space on your device.", Toast.LENGTH_SHORT).show();
                                e.printStackTrace();
                            }
                            dialog.dismiss();
                        }
                    }
                });

    }

    //Stop all currently playing sounds
    private static void pause(Context context, Map<SoundButton, MediaPlayer> playerMap, Map<Button, SoundButton> soundMap, List<SoundButton> currentlyPlaying) {
        for (SoundButton sb : currentlyPlaying) {
            playerMap.get(sb).pause();
            try {
                Button button = (Button) Helper.getKeyFromValue(soundMap, sb);
                button.setBackground(context.getResources().getDrawable(sb.getImageOffID()));
            } catch (NullPointerException npe) {
                System.err.println("Error pausing sound, no matching button was found for the sound.");
            }
        }
    }

    //Resume most recently played sounds
    private static void resume(Context context, Map<SoundButton, MediaPlayer> playerMap, Map<Button, SoundButton> soundMap, List<SoundButton> currentlyPlaying) {
        for (SoundButton sb : currentlyPlaying) {
            playerMap.get(sb).start();
            playerMap.get(sb).setLooping(true);
            try {
                Button button = (Button) Helper.getKeyFromValue(soundMap, sb);
                button.setBackground(context.getResources().getDrawable(sb.getImageOnID()));
            } catch (NullPointerException npe) {
                System.err.println("Error resuming sound, no matching button was found for the sound.");
            }
        }
    }
}
