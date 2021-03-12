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
            ((EditText) findViewById(R.id.option1_field)).setText(getIntent().getExtras().getString("option1"));
            ((EditText) findViewById(R.id.option2_field)).setText(getIntent().getExtras().getString("option2"));
            ((EditText) findViewById(R.id.option3_field)).setText(getIntent().getExtras().getString("option3"));
        }

        findViewById(R.id.save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Inflate toast XML layout
                View layout = getLayoutInflater().inflate(R.layout.toast_layout, (ViewGroup) findViewById(R.id.toast_layout_root));
                TextView text = (TextView) layout.findViewById(R.id.text);

                String q = ((EditText) findViewById(R.id.question_field)).getText().toString();
                String a = ((EditText) findViewById(R.id.answer_field)).getText().toString();
                String o1 = ((EditText) findViewById(R.id.option1_field)).getText().toString();
                String o2 = ((EditText) findViewById(R.id.option2_field)).getText().toString();
                String o3 = ((EditText) findViewById(R.id.option3_field)).getText().toString();

                if(q.equals("") || a.equals("") || o1.equals("") || o2.equals("") || o3.equals("")) {
                    text.setText("All fields must be filled");
                    Toast toast = new Toast(getApplicationContext());
                    toast.setView(layout);
                    toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                    toast.show();
                } else {
                    Intent data = new Intent(AddCardActivity.this, MainActivity.class);
                    data.putExtra("question", q);
                    data.putExtra("answer", a);
                    data.putExtra("option1", o1);
                    data.putExtra("option2", o2);
                    data.putExtra("option3", o3);
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