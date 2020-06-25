package com.example.gameofcodes;


import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class cppquestions extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();

    private TextView question;
    private LinearLayout optionscontainer;
    private Button next;
    private int count=0;
    private  List<questionmodel> list;
    private int position=0;
    private int score=0;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cppquestions);
        Toolbar toolbar= findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        question= findViewById(R.id.question);
        optionscontainer= findViewById(R.id.optionscontainer);
        next= findViewById(R.id.next);
        id= getIntent().getIntExtra("id",1);

        final MediaPlayer ring6= MediaPlayer.create(cppquestions.this,R.raw.needle);
        ring6.start();
        ring6.setLooping(true);

        list = new ArrayList<>();
        myRef.child("categories").child("cppquestions").child("questions").orderByChild("id").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                    list.add(snapshot.getValue(questionmodel.class));
                }
                if(list.size()>0){

                    for(int i=0;i<4;i++){
                        optionscontainer.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                checkAnswer((Button)v);

                            }
                        });
                    }
                    playAnim(question,0,list.get(position).getQuestion());
                    next.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            next.setEnabled(false);
                            next.setAlpha(0.7f);
                            enabledOption(true);
                            position++;
                            if(position== list.size()){
                                //score activity
                                return;
                            }
                            count=0;
                            playAnim(question,0,list.get(position).getQuestion());
                        }
                    });
                }
                else {
                    finish();
                    Toast.makeText(cppquestions.this, "no questions", Toast.LENGTH_SHORT).show();
                    ring6.pause();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(cppquestions.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });






    }
    private void playAnim(final View view, final int value, final String data){

        view.animate().alpha(value).scaleX(value).scaleY(value).setDuration(500).setStartDelay(100).setInterpolator(new DecelerateInterpolator()).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                if(value==0 && count<4){
                    String option="";
                    if(count==0 ){
                        option=list.get(position).getOptionA();
                    }
                    else if(count==1){
                        option=list.get(position).getOptionB();
                    }
                    else if(count==2){
                        option=list.get(position).getOptionC();
                    }
                    else if (count==3){
                        option=list.get(position).getOptionD();
                    }
                    playAnim(optionscontainer.getChildAt(count),0,option);
                    count++;
                }
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if(value==0){
                    try {
                        ((TextView)view).setText(data);
                    }
                    catch (ClassCastException ex){
                        ((Button)view).setText(data);
                    }
                    view.setTag(data);
                    playAnim(view,1,data);
                }

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void checkAnswer(Button selectedOption){
        enabledOption(false);
        next.setEnabled(true);
        next.setAlpha(1);
        if(selectedOption.getText().toString().equals(list.get(position).getCorrectAns())){
            //correct
            score++;
            selectedOption.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#4CAF50")));
        }
        else {
            //incorrect
            selectedOption.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#ff0000")));
            Button correctoption= (Button) optionscontainer.findViewWithTag(list.get(position).getCorrectAns());
            correctoption.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#4CAF50")));

        }

    }
    private void enabledOption(boolean enable){

        for(int i=0;i<4;i++){
            optionscontainer.getChildAt(i).setEnabled(enable);
            if(enable){
                optionscontainer.getChildAt(i).setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#ffffff")));

            }
        }

    }
}


