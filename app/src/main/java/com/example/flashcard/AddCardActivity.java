package com.example.flashcard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
                // Inflate toast XML layout
                View layout = getLayoutInflater().inflate(R.layout.toast_layout, (ViewGroup) findViewById(R.id.toast_layout_root));
                TextView text = (TextView) layout.findViewById(R.id.text);

                String q = ((EditText) findViewById(R.id.question_field)).getText().toString();
                String a = ((EditText) findViewById(R.id.answer_field)).getText().toString();
                if(q.equals("") && a.equals("")) {
                    text.setText("Both fields must be filled");
                    Toast toast = new Toast(getApplicationContext());
                    toast.setView(layout);
                    toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                    toast.show();
                } else if(q.equals("")) {
                    text.setText("Question field must be filled");
                    Toast toast = new Toast(getApplicationContext());
                    toast.setView(layout);
                    toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                    toast.show();
                } else if(a.equals("")) {
                    text.setText("Answer field must be filled");
                    Toast toast = new Toast(getApplicationContext());
                    toast.setView(layout);
                    toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                    toast.show();
                } else {
                    String question = ((EditText) findViewById(R.id.question_field)).getText().toString();
                    String answer = ((EditText) findViewById(R.id.answer_field)).getText().toString();
                    Intent data = new Intent(AddCardActivity.this, MainActivity.class);
                    data.putExtra("question", question);
                    data.putExtra("answer", answer);
                    setResult(RESULT_OK, data);
                    finish();
                }
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