package com.joeparker.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private AnimationDrawable androidAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView androidImage = (ImageView)findViewById(R.id.android);
        androidImage.setBackgroundResource(R.drawable.android_animate);
        androidAnim = (AnimationDrawable)androidImage.getBackground();

        final Button animationButton = (Button)findViewById(R.id.animate);
        View.OnClickListener clicked;
        clicked = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                androidAnim.stop();
                androidAnim.start();
            }
        };
        animationButton.setOnClickListener(clicked);
    }
}
