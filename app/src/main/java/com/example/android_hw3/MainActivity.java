package com.example.android_hw3;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    private EditText QuestionNumber;
    private Button ButtonStartQuiz;
    int Questions=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final MediaPlayer elevatormusic = MediaPlayer.create(MainActivity.this,R.raw.music);
        elevatormusic.start();
        setContentView(R.layout.activity_main);
        ButtonStartQuiz= (Button) findViewById(R.id.ButtonStart);
        QuestionNumber = (EditText) findViewById(R.id.QuestionNumberInput);
        ButtonStartQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTriviaActivity();
            }
        });
    }
    public void openTriviaActivity(){
        Intent intent = new Intent(this, QuestionActivity.class);
        if(!QuestionNumber.getText().toString().isEmpty())
        Questions=Integer.parseInt(QuestionNumber.getText().toString());
        else
            Questions=10;
        intent.putExtra("Questions",Questions);
        // intent.setFlags(intent.getFlags() | Intent.FLAG_ACTIVITY_NO_HISTORY); // Adds the FLAG_ACTIVITY_NO_HISTORY flag
        startActivity(intent);
    }
}