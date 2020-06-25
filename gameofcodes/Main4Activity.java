package com.example.gameofcodes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

public class Main4Activity extends AppCompatActivity {
    private Button startbtn2;
    VideoView video3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        video3= (VideoView)findViewById(R.id.video3);

        String path= "android.resource://com.example.gameofcodes/"+ R.raw.kingsland;
        Uri u= Uri.parse(path);
        video3.setVideoURI(u);
        video3.start();

        video3.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
            }
        });
        Button startbtn2= (Button)findViewById(R.id.start2);

        final MediaPlayer ring5= MediaPlayer.create(Main4Activity.this,R.raw.baratheon);
        ring5.start();
        ring5.setLooping(true);
        startbtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(Main4Activity.this, jsquestions.class);
                startActivity(intent);
                ring5.pause();

            }
        });


    }
    @Override
    protected void onResume(){

        video3.resume();
        super.onResume();
    }

    @Override
    protected void onPause(){

        video3.suspend();
        super.onPause();
    }

    @Override
    protected void onDestroy(){

        video3.stopPlayback();
        super.onDestroy();
    }
}
