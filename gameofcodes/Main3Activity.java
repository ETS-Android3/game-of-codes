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

public class Main3Activity extends AppCompatActivity {
    private Button startbtn;
    VideoView video5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        video5= (VideoView)findViewById(R.id.video5);

        String path= "android.resource://com.example.gameofcodes/"+ R.raw.mereen;
        Uri u= Uri.parse(path);
        video5.setVideoURI(u);
        video5.start();

        video5.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
            }
        });
        Button startbtn= (Button)findViewById(R.id.start1);

        final MediaPlayer ring3= MediaPlayer.create(Main3Activity.this,R.raw.targaryengot);
        ring3.start();
        ring3.setLooping(true);

        startbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(Main3Activity.this, pyquestions.class);
                startActivity(intent);
                ring3.pause();

            }
        });


    }
    @Override
    protected void onResume(){

        video5.resume();
        super.onResume();
    }

    @Override
    protected void onPause(){

        video5.suspend();
        super.onPause();
    }

    @Override
    protected void onDestroy(){

        video5.stopPlayback();
        super.onDestroy();
    }

}

