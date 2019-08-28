package com.joeparker.myapplication;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;

import java.io.IOException;
import java.io.Serializable;
import java.util.Objects;

public class SoundButton implements Serializable {

    private int rawSound;
    private int imageOffID;
    private int imageOnID;
 //   private Drawable imageOff;
 //   private Drawable imageOn;
 //   private MediaPlayer player;

    public SoundButton(int rawSound, int imageOffID, int imageOnID) {//Context context, int rawSound, Drawable imageOff, Drawable imageOn) {
        setRawSound(rawSound);
        setImageOffID(imageOffID);
        setImageOnID(imageOnID);
   //     setImageOff(imageOff);
   //     setImageOn(imageOn);
   //     setPlayer(MediaPlayer.create(context, rawSound));
    }

    public int getRawSound() {
        return rawSound;
    }

    public void setRawSound(int sound) {
        this.rawSound = sound;
    }

    public int getImageOffID() {
        return imageOffID;
    }

    public void setImageOffID(int imageOffID) {
        this.imageOffID = imageOffID;
    }

    public int getImageOnID() {
        return imageOnID;
    }

    public void setImageOnID(int imageOnID) {
        this.imageOnID = imageOnID;
    }

    /*
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
*/
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SoundButton that = (SoundButton) o;
        return Objects.equals(getRawSound(), that.getRawSound()) &&
                Objects.equals(getImageOffID(), that.getImageOffID()) &&
                Objects.equals(getImageOnID(), that.getImageOnID());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRawSound(), getImageOffID(), getImageOnID());
    }

    private void writeObject(java.io.ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
    }

    private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
    }

}
