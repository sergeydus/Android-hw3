package com.example.android_hw3;

import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import cz.msebera.android.httpclient.Header;

public class Server {

    public static class Question{
        String question;
        String correctAnswer;
        ArrayList<String> answers = new ArrayList<>();

        public Question(){}
        public Question(JSONObject first){
            try {
                question = first.getString("question");
                correctAnswer = first.getString("correct_answer");
                answers.add(correctAnswer);
                JSONArray wrong_ans = first
                        .getJSONArray("incorrect_answers");
                for (int i = 0; i < wrong_ans.length(); i++) {
                    answers.add(wrong_ans.getString(i));
                }
                shuffle();
            } catch (Exception e){
                Log.e("error", e.toString());
            }
        }
        public void shuffle(){
            Random rnd= ThreadLocalRandom.current();
            for (int i = answers.size() - 1; i > 0; i--)
            {
                int index = rnd.nextInt(i + 1);
                // Simple swap
                String a = answers.get(index);
                answers.set(index,answers.get(i));
                answers.set(i,a);
            }
        }
    }

    public interface HandleQuestion{
        void handleQuestion(Question q);
    }

    public static void getTriviaQuestion(final HandleQuestion h){
        String url = "https://opentdb.com/api.php?amount=1&category=9&difficulty=medium&type=multiple";
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(url, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers,
                                  JSONObject response) {
                try {
                    JSONArray results = response.getJSONArray("results");
                    //if results.length() == 0 ?
                    JSONObject first = results.getJSONObject(0);
                    h.handleQuestion(new Question(first));
                } catch (Exception e){

                }
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray timeline) {
                //handle array
            }
        });
    }
}
