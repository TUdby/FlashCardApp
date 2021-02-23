package com.example.flashcard;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView flashcardQuestion = findViewById(R.id.question);
        TextView flashcardAnswer = findViewById(R.id.answer);
        TextView option1 = findViewById(R.id.option1);
        TextView option2 = findViewById(R.id.option2);
        TextView option3 = findViewById(R.id.option3);

        flashcardQuestion.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                flashcardAnswer.setVisibility(View.VISIBLE);
                flashcardQuestion.setVisibility(View.INVISIBLE);
            }
        });

        flashcardAnswer.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                flashcardAnswer.setVisibility(View.INVISIBLE);
                flashcardQuestion.setVisibility(View.VISIBLE);
            }
        });

        option1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                option1.setBackgroundColor(getResources().getColor(R.color.red, null));
                option3.setBackgroundColor(getResources().getColor(R.color.green, null));
            }
        });

        option2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                option2.setBackgroundColor(getResources().getColor(R.color.red, null));
                option3.setBackgroundColor(getResources().getColor(R.color.green, null));
            }
        });

        option3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                option3.setBackgroundColor(getResources().getColor(R.color.green, null));
            }
        });
        final boolean[] isShowingAnswers = {true};
        ImageView showOptions = findViewById(R.id.toggle_choices_visibility);
        showOptions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isShowingAnswers[0]) {
                    showOptions.setImageResource(R.drawable.ic_iconmonstr_eye_5);
                    option1.setVisibility(View.INVISIBLE);
                    option2.setVisibility(View.INVISIBLE);
                    option3.setVisibility(View.INVISIBLE);
                    isShowingAnswers[0] = false;
                } else {
                    showOptions.setImageResource(R.drawable.ic_iconmonstr_eye_8);
                    option1.setVisibility(View.VISIBLE);
                    option2.setVisibility(View.VISIBLE);
                    option3.setVisibility(View.VISIBLE);
                    isShowingAnswers[0] = true;
                }
            }
        });

    }
}