package com.joeparker.myapplication;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SoundManager extends Activity {

    private SoundPool soundPool;
    private int maxStreams;
    private int[] sounds;
    private Map<Integer, Integer> currentlyPlaying = new HashMap<>();

    public SoundManager(int maxStreams, int[] rawSounds, Context context) {
        this.maxStreams = maxStreams;
        this.sounds = new int[rawSounds.length];
      //  this.isPlaying = new boolean[rawSounds.length];

        //Use either factory or constructor depending on SDK version
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            soundPool = new SoundPool.Builder().setMaxStreams(maxStreams).build();
        }
        else {
            soundPool = new SoundPool(maxStreams, AudioManager.STREAM_MUSIC, 0);
        }

        //Load sounds
        for (int i = 0; i < sounds.length; i++){
            sounds[i] = soundPool.load(context, rawSounds[i], 1);
        }
    }

    //WARNING: does not update the isPlaying flag
    public void playSound(int sound) {
        soundPool.play(sounds[sound], 1, 1, 1, 0, 1.0f);
    }

    public void playLoop(int soundIndex) {
        Integer streamID = soundPool.play(sounds[soundIndex], 1, 1, 1, -1, 1.0f);
        currentlyPlaying.put(soundIndex, streamID);
    }

    public void pause(Integer index) {
        soundPool.pause(getStreamID(index));
        currentlyPlaying.remove(index);
    }

    public boolean isPlaying(Integer index) {
        if (currentlyPlaying.get(index) == null) return false;
        return currentlyPlaying.get(index) != 0;
    }

    private int getStreamID(int index) {
        return currentlyPlaying.get(index);
    }

    public int getMaxStreams() {
        return maxStreams;
    }

    public int[] getSounds() {
        return sounds;
    }

    public void setSounds(int[] sounds) {
        this.sounds = sounds;
    }

    public boolean addSound(int sound) {
        try {
            int size = sounds.length;
            int[] temp = new int[size + 1];
            for (int i = 0; i < size; i++) {
                temp[i] = sounds[i];
            }
            temp[size] = sound;
            this.sounds = temp;
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public final void close() {
        sounds = null;
        soundPool.release();
        soundPool = null;
    }


}
