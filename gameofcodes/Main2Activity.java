package com.example.gameofcodes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.VideoView;

public class Main2Activity extends AppCompatActivity {


    VideoView video2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        video2 = (VideoView) findViewById(R.id.video2);

        String path = "android.resource://com.example.gameofcodes/" + R.raw.winterfell;
        Uri u = Uri.parse(path);
        video2.setVideoURI(u);
        video2.start();

        video2.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
            }
        });


        ImageView imgview = (ImageView) findViewById(R.id.imageView2);
        imgview.bringToFront();
        imgview.setAlpha(0.7f);
        imgview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(Main2Activity.this, Main5Activity.class);
                startActivity(myIntent);
            }

        });

        ImageView img2view = (ImageView) findViewById(R.id.imageView3);
        img2view.bringToFront();
        img2view.setAlpha(0.7f);
        img2view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(Main2Activity.this, Main3Activity.class);
                startActivity(myIntent);
            }

        });

        ImageView img3view = (ImageView) findViewById(R.id.imageView4);
        img3view.bringToFront();
        img3view.setAlpha(0.7f);
        img3view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(Main2Activity.this, Main4Activity.class);
                startActivity(myIntent);
            }

        });

        ImageView img4view = (ImageView) findViewById(R.id.imageView7);
        img4view.bringToFront();
        img4view.setAlpha(0.7f);
        img4view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(Main2Activity.this, Main6Activity.class);
                startActivity(myIntent);
            }

        });


       
    }

    @Override
    protected void onResume(){

        video2.resume();
        super.onResume();
    }

    @Override
    protected void onPause(){

        video2.suspend();
        super.onPause();
    }

    @Override
    protected void onDestroy(){

        video2.stopPlayback();
        super.onDestroy();
    }

}
