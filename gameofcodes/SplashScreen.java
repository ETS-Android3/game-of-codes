package com.example.gameofcodes;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.view.MotionEvent;
import android.widget.VideoView;
import android.os.Bundle;

public class SplashScreen extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            VideoView videoHolder = new VideoView(this);
            setContentView(videoHolder);
            Uri video = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.gocsplash);
            videoHolder.setVideoURI(video);

            videoHolder.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                public void onCompletion(MediaPlayer mp) {
                    jump();
                }
            });
            videoHolder.start();
        } catch (Exception ex) {
            jump();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        jump();
        return true;
    }

    private void jump() {
        if (isFinishing())
            return;
        startActivity(new Intent(SplashScreen.this, MainActivity.class));
        finish();

    }
}
