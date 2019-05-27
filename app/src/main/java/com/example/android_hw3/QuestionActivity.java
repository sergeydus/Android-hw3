
package com.example.android_hw3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android_hw3.R;
import com.example.android_hw3.Server;

import java.util.ArrayList;

public class QuestionActivity extends AppCompatActivity {

    TextView question;
    ListView answersList;
    ArrayAdapter<String> adapter;
    Server.Question currentQuestion;
    int QuestionNumber=0; //amount of questions
    int answered=0; //amount of answered questions
    int answeredCorrectly=0; // amount of correctly answered questions
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        question = findViewById(R.id.text);
        answersList = findViewById(R.id.answersList);
        QuestionNumber=getIntent().getIntExtra("Questions",10);
        adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                new ArrayList<String>());
        answersList.setAdapter(adapter);
        answersList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String chosen = (String)adapterView.getItemAtPosition(position);
                if(answered<QuestionNumber) {
                    if (currentQuestion.correctAnswer.equals(chosen)) {
                   //     Toast.makeText(adapterView.getContext(), "right!", Toast.LENGTH_LONG).show();
                        answered++;
                        answeredCorrectly++;
                        getQuestion(view);
                    } else {
                    //    Toast.makeText(adapterView.getContext(), "wrong!", Toast.LENGTH_LONG).show();
                        answered++;
                        getQuestion(view);
                    }
                }
                else{
                    //TODO: go to result page , if all answers are correct go to the BONUS PAGE
                }
            }
        });
       getQuestion(this.findViewById(android.R.id.content));


    }

    public void getQuestion(View view) {
        Server.getTriviaQuestion(new Server.HandleQuestion() {
            @Override
            public void handleQuestion(Server.Question q) {
                currentQuestion = q;
                question.setText(Html.fromHtml(q.question));
                adapter.clear();
                for(String a: q.answers){
                    adapter.add(a);
                }
                adapter.notifyDataSetChanged();
            }
        });
    }
}
