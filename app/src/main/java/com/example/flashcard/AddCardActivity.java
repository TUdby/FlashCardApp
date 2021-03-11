package com.example.flashcard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class AddCardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card);

        if(getIntent().hasExtra("question")) {
            ((EditText) findViewById(R.id.question_field)).setText(getIntent().getExtras().getString("question"));
            ((EditText) findViewById(R.id.answer_field)).setText(getIntent().getExtras().getString("answer"));
        }

        findViewById(R.id.save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String question = ((EditText) findViewById(R.id.question_field)).getText().toString();
                String answer = ((EditText) findViewById(R.id.answer_field)).getText().toString();
                Intent data = new Intent(AddCardActivity.this, MainActivity.class);
                data.putExtra("question", question);
                data.putExtra("answer", answer);
                setResult(RESULT_OK, data);
                finish();
            }
        });

        findViewById(R.id.close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}