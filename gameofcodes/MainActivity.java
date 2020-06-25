package com.example.gameofcodes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.gameofcodes.R;


import android.widget.EditText;
import android.widget.TextView;

import com.example.gameofcodes.Main2Activity;

public class MainActivity extends AppCompatActivity {
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button=(Button)findViewById(R.id.button);
        button.setAlpha(0.5f);

        final MediaPlayer ring= MediaPlayer.create(MainActivity.this,R.raw.gotaudio);
        ring.start();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(MainActivity.this, Main2Activity.class);
                startActivity(intent);
                ring.pause();

            }
        });




    }
}
