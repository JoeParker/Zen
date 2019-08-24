package com.joeparker.myapplication;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;

import java.util.Objects;

public class SoundButton {

    private int rawSound;
    private Drawable imageOff;
    private Drawable imageOn;
    private MediaPlayer player;

    public SoundButton(Context context, int rawSound, Drawable imageOff, Drawable imageOn) {
        setRawSound(rawSound);
        setImageOff(imageOff);
        setImageOn(imageOn);
        setPlayer(MediaPlayer.create(context, rawSound));
    }

    public int getRawSound() {
        return rawSound;
    }

    public void setRawSound(int sound) {
        this.rawSound = sound;
    }

    public Drawable getImageOff() {
        return imageOff;
    }

    public void setImageOff(Drawable imageOff) {
        this.imageOff = imageOff;
    }

    public Drawable getImageOn() {
        return imageOn;
    }

    public void setImageOn(Drawable imageOn) {
        this.imageOn = imageOn;
    }

    public MediaPlayer getPlayer() {
        return player;
    }

    public void setPlayer(MediaPlayer player) {
        this.player = player;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SoundButton that = (SoundButton) o;
        return Objects.equals(getRawSound(), that.getRawSound()) &&
                Objects.equals(getImageOff(), that.getImageOff()) &&
                Objects.equals(getImageOn(), that.getImageOn());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRawSound(), getImageOff(), getImageOn());
    }
}
