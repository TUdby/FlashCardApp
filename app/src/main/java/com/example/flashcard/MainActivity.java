package com.example.flashcard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    FlashcardDatabase flashcardDatabase;
    List<Flashcard> allFlashcards;
    int cardIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        flashcardDatabase = new FlashcardDatabase(getApplicationContext());
        allFlashcards = flashcardDatabase.getAllCards();

        TextView flashcardQuestion = findViewById(R.id.question);
        TextView flashcardAnswer = findViewById(R.id.answer);
        //TextView option1 = findViewById(R.id.option1);
        //TextView option2 = findViewById(R.id.option2);
        //TextView option3 = findViewById(R.id.option3);

        cardIndex = -1;
        if(!allFlashcards.isEmpty()) {
            flashcardQuestion.setText(allFlashcards.get(0).getQuestion());
            flashcardAnswer.setText(allFlashcards.get(0).getAnswer());
            cardIndex = 0;
        }

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

        /*
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
        */

        findViewById(R.id.trash_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(allFlashcards.size() == 1) {
                    flashcardDatabase.deleteCard(((TextView) findViewById(R.id.question)).getText().toString());
                    allFlashcards = flashcardDatabase.getAllCards();

                    TextView flashcardQuestion = findViewById(R.id.question);
                    TextView flashcardAnswer = findViewById(R.id.answer);

                    flashcardQuestion.setVisibility(View.VISIBLE);
                    flashcardAnswer.setVisibility(View.INVISIBLE);

                    flashcardQuestion.setText("Create Card!");
                    flashcardAnswer.setText("Create Card!");
                    cardIndex = -1;
                }
                if(!allFlashcards.isEmpty()) {
                    flashcardDatabase.deleteCard(((TextView) findViewById(R.id.question)).getText().toString());
                    allFlashcards = flashcardDatabase.getAllCards();

                    if(cardIndex != 0)
                        cardIndex --;

                    TextView flashcardQuestion = findViewById(R.id.question);
                    TextView flashcardAnswer = findViewById(R.id.answer);
                    Flashcard flashcard = allFlashcards.get(cardIndex);

                    flashcardQuestion.setVisibility(View.VISIBLE);
                    flashcardAnswer.setVisibility(View.INVISIBLE);

                    flashcardQuestion.setText(flashcard.getQuestion());
                    flashcardAnswer.setText(flashcard.getAnswer());
                }
            }
        });

        findViewById(R.id.next_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(allFlashcards.isEmpty())
                    return;

                TextView flashcardQuestion = findViewById(R.id.question);
                TextView flashcardAnswer = findViewById(R.id.answer);

                cardIndex ++;
                if(cardIndex >= allFlashcards.size()) {
                    Snackbar.make(flashcardQuestion,
                            "Last card reached, returning to start.",
                            Snackbar.LENGTH_SHORT).show();
                    cardIndex = 0;
                }

                Flashcard flashcard = allFlashcards.get(cardIndex);

                flashcardQuestion.setVisibility(View.VISIBLE);
                flashcardAnswer.setVisibility(View.INVISIBLE);

                flashcardQuestion.setText(flashcard.getQuestion());
                flashcardAnswer.setText(flashcard.getAnswer());
            }
        });

        findViewById(R.id.add_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddCardActivity.class);
                MainActivity.this.startActivityForResult(intent, 100);
            }
        });

        findViewById(R.id.edit_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddCardActivity.class);

                String question = ((TextView) findViewById(R.id.question)).getText().toString();
                String answer = ((TextView) findViewById(R.id.answer)).getText().toString();
                //String option1 = ((TextView) findViewById(R.id.option1)).getText().toString();
                //String option2 = ((TextView) findViewById(R.id.option2)).getText().toString();
                //String option3 = ((TextView) findViewById(R.id.option3)).getText().toString();

                intent.putExtra("question", question);
                intent.putExtra("answer", answer);
                //intent.putExtra("option1", option1);
                //intent.putExtra("option2", option2);
                //intent.putExtra("option3", option3);

                MainActivity.this.startActivityForResult(intent, 100);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == RESULT_OK) {
            String question = data.getExtras().getString("question");
            ((TextView) findViewById(R.id.question)).setText(question);

            String answer = data.getExtras().getString("answer");
            ((TextView) findViewById(R.id.answer)).setText(answer);

            /*
            String option1 = data.getExtras().getString("option1");
            ((TextView) findViewById(R.id.option1)).setText(option1);

            String option2 = data.getExtras().getString("option2");
            ((TextView) findViewById(R.id.option2)).setText(option2);

            String option3 = data.getExtras().getString("option3");
            ((TextView) findViewById(R.id.option3)).setText(option3);
            */

            flashcardDatabase.insertCard(new Flashcard(question, answer));
            allFlashcards = flashcardDatabase.getAllCards();

            Snackbar.make(findViewById(R.id.question),
                    "Card Created Successfully!",
                    Snackbar.LENGTH_SHORT)
                    .show();
        }
    }
}