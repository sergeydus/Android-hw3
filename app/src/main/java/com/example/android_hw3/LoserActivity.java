package com.example.android_hw3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LoserActivity extends AppCompatActivity {
    private Button restart;
    private TextView wamba;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loser);
        wamba=findViewById(R.id.TextAnswer);
        String wambefet=String.valueOf(getIntent().getIntExtra("Answered",10));
        wamba.setText("Correctly Answered:"+wambefet);
        restart=findViewById(R.id.ButtonReStart);

        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTriviaActivity();
            }
        });
    }

    public void openTriviaActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        // intent.setFlags(intent.getFlags() | Intent.FLAG_ACTIVITY_NO_HISTORY); // Adds the FLAG_ACTIVITY_NO_HISTORY flag
        startActivity(intent);
    }
}
