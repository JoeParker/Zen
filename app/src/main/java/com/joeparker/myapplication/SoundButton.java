package com.joeparker.myapplication;

import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;

import java.util.Objects;

public class SoundButton {

    private MediaPlayer player;
    private Drawable imageOff;
    private Drawable imageOn;

    public SoundButton(MediaPlayer player, Drawable imageOff, Drawable imageOn) {
        setPlayer(player);
        setImageOff(imageOff);
        setImageOn(imageOn);
    }

    public MediaPlayer getPlayer() {
        return player;
    }

    public void setPlayer(MediaPlayer player) {
        this.player = player;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SoundButton that = (SoundButton) o;
        return Objects.equals(getPlayer(), that.getPlayer()) &&
                Objects.equals(getImageOff(), that.getImageOff()) &&
                Objects.equals(getImageOn(), that.getImageOn());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPlayer(), getImageOff(), getImageOn());
    }
}
