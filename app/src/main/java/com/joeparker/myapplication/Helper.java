package com.joeparker.myapplication;

/* Helper class for useful common functions
 */

import android.media.MediaPlayer;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public abstract class Helper {

    //Get currently playing sounds in the SoundButton map
    public static List<SoundButton> getCurrentlyPlaying(Map<Button, SoundButton> m) {
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
                System.err.println("Error getting currently playing sounds, unable to cast SoundButton object.");
            }
        }
        return nowPlaying;
    }

    //Get first map key from it's matching value
    public static Object getKeyFromValue(Map m, Object value) {
            Set<Map.Entry> entries = m.entrySet();
            for (Map.Entry entry : entries) {
                if (Objects.equals(value, entry.getValue())) {
                    return entry.getKey();
                }
            }
            return null; //Returns null if no key pair is found for the value
    }


}
