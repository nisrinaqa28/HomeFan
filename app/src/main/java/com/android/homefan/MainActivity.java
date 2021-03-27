package com.android.homefan;

import androidx.appcompat.app.AppCompatActivity;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.animation.LinearInterpolator;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {
    ToggleButton toggleButton;
    ImageView imageView;
    ObjectAnimator rotateAnimator;
    Switch switchButton;
    SeekBar seekBar;
    final int SPEED[] = {0, 5000, 3000, 1000};
    GradientDrawable gd = new GradientDrawable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView) findViewById(R.id.fanImage);
        toggleButton = (ToggleButton) findViewById(R.id.onOffButton);
        rotateAnimator=ObjectAnimator.ofFloat(imageView, "rotation", 0,360);
        rotateAnimator.setDuration(SPEED[1]);
        rotateAnimator.setRepeatCount(ValueAnimator.INFINITE);
        rotateAnimator.setInterpolator(new LinearInterpolator());
        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    rotateAnimator.start();
                    seekBar = (SeekBar) findViewById(R.id.speedSeekBar);
                    seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                        @Override
                        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                            switch(progress) {
                                case 0:
                                    rotateAnimator.setDuration(SPEED[0]);
                                    break;
                                case 1:
                                    rotateAnimator.setDuration(SPEED[1]);
                                    break;
                                case 2:
                                    rotateAnimator.setDuration(SPEED[2]);
                                    break;
                                case 3:
                                    rotateAnimator.setDuration(SPEED[3]);
                                    break;
                            }
                        }
                        @Override
                        public void onStartTrackingTouch(SeekBar seekBar) {
                        }
                        @Override
                        public void onStopTrackingTouch(SeekBar seekBar) {
                        }
                    });
                } else {
                    rotateAnimator.end();
                }
            }
        });

        switchButton = (Switch) findViewById(R.id.lightSwitch);
        gd.setShape(GradientDrawable.RECTANGLE);
        gd.setGradientType(GradientDrawable.RADIAL_GRADIENT);
        gd.setGradientRadius(330);
        switchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    gd.setColors(new int[]{ Color.YELLOW , Color.TRANSPARENT });
                    imageView.setBackground(gd);
                } else {
                    imageView.setBackgroundColor(Color.TRANSPARENT);
                }
            }
        });

    }
}