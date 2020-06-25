package com.example.gameofcodes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

public class Main5Activity extends AppCompatActivity {
    private Button startbtn3;
    VideoView video6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        video6= (VideoView)findViewById(R.id.video6);

        String path= "android.resource://com.example.gameofcodes/"+ R.raw.stark;
        Uri u= Uri.parse(path);
        video6.setVideoURI(u);
        video6.start();

        video6.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
            }
        });
        Button startbtn3= (Button)findViewById(R.id.start3);

        final MediaPlayer ring4= MediaPlayer.create(Main5Activity.this,R.raw.starkgot);
        ring4.start();
        ring4.setLooping(true);
        startbtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(Main5Activity.this, cppquestions.class);
                startActivity(intent);
                ring4.pause();

            }
        });

    }
    @Override
    protected void onResume(){

        video6.resume();
        super.onResume();
    }

    @Override
    protected void onPause(){

        video6.suspend();
        super.onPause();
    }

    @Override
    protected void onDestroy(){

        video6.stopPlayback();
        super.onDestroy();
    }

}
