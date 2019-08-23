package com.joeparker.myapplication;

import android.graphics.drawable.Drawable;

import java.util.Objects;

public class SoundButton {

    private int rawSound;
    private Drawable imageOff;
    private Drawable imageOn;

    public SoundButton(int rawSound, Drawable imageOff, Drawable imageOn) {
        setRawSound(rawSound);
        setImageOff(imageOff);
        setImageOn(imageOn);
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
