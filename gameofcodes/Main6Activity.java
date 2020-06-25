package com.example.gameofcodes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

public class Main6Activity extends AppCompatActivity {
    private Button startbtn4;
    VideoView video4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);
        video4= (VideoView)findViewById(R.id.video4);

        String path= "android.resource://com.example.gameofcodes/"+ R.raw.lannister;
        Uri u= Uri.parse(path);
        video4.setVideoURI(u);
        video4.start();

        video4.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
            }
        });
        Button startbtn4= (Button)findViewById(R.id.start4);

        final MediaPlayer ring2= MediaPlayer.create(Main6Activity.this,R.raw.lannistergot);
        ring2.start();
        ring2.setLooping(true);

        startbtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(Main6Activity.this, javaquestions.class);
                startActivity(intent);
                ring2.pause();

            }
        });

    }
}
