package com.joeparker.myapplication;

import java.io.IOException;
import java.io.Serializable;
import java.util.Objects;

public class SoundButton implements Serializable {

    private int rawSound;
    private int imageOffID;
    private int imageOnID;

    public SoundButton(int rawSound, int imageOffID, int imageOnID) {
        setRawSound(rawSound);
        setImageOffID(imageOffID);
        setImageOnID(imageOnID);
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
