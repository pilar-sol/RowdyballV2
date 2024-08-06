package edu.utsa.cs3443.rowdyballv2;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
/**
 * The QuestionActivity class handles the screen where users can input their questions.
 * It navigates to the AnswerActivity when a question is submitted.
 */
public class QuestionActivity extends AppCompatActivity {

    private EditText questionEditText;
    private ImageView eightBallImageView;
    private Button infoButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        questionEditText = findViewById(R.id.questionEditText);
        eightBallImageView = findViewById(R.id.eightBallImageView);
        infoButton = findViewById(R.id.infoButton);

        eightBallImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String question = questionEditText.getText().toString().trim();
                if (!question.isEmpty()) {
                    Intent intent = new Intent(QuestionActivity.this, AnswerActivity.class);
                    intent.putExtra("question", question);
                    startActivity(intent);
                } else {
                    Toast.makeText(QuestionActivity.this, "Please enter a question", Toast.LENGTH_SHORT).show();
                }
            }
        });

        infoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showInfoPopup();
            }
        });
    }

    private void showInfoPopup() {
        new AlertDialog.Builder(this)
                .setTitle("About RowdyBall")
                .setMessage("RowdyBall is a fun and interactive 8-ball game where you can ask any question and get an answer. Enjoy!")
                .setPositiveButton("OK", null)
                .show();
    }
}
