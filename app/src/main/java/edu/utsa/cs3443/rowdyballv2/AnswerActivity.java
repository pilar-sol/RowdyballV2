package edu.utsa.cs3443.rowdyballv2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import edu.utsa.cs3443.rowdyballv2.model.Response;
/**
 * The AnswerActivity class displays the answer to the user's question.
 * It allows users to change the language of the answer and ask another question.
 */
public class AnswerActivity extends AppCompatActivity {

    private TextView answerTextView;
    private Button infoButton;
    private Button languagesButton;
    private Button askAnotherQuestionButton;
    private Random random;
    private List<Response> answers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);
        // Initialize UI elements
        answerTextView = findViewById(R.id.answerTextView);
        infoButton = findViewById(R.id.infoButton);
        languagesButton = findViewById(R.id.languagesButton);
        askAnotherQuestionButton = findViewById(R.id.askAnotherQuestionButton);
        random = new Random();

        languagesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLanguagePopup();
            }
        });

        infoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showInfoPopup();
            }
        });

        askAnotherQuestionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                askAnotherQuestion();
            }
        });

        // Load the selected language responses
        loadResponses();

        // Get a random response and display it
        displayRandomResponse();
    }
    /**
     * Loads responses from the selected language file.
     */
    private void loadResponses() {
        SharedPreferences prefs = getSharedPreferences("RowdyBallPrefs", MODE_PRIVATE);
        String selectedLanguage = prefs.getString("selected_language", "english_responses.txt");
        answers = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(getAssets().open(selectedLanguage)))) {
            String line;
            while ((line = reader.readLine()) != null) {
                answers.add(new Response(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void displayRandomResponse() {
        if (answers != null && !answers.isEmpty()) {
            int index = random.nextInt(answers.size());
            answerTextView.setText(answers.get(index).getText());
        }
    }

    private void showInfoPopup() {
        new AlertDialog.Builder(this)
                .setTitle("About RowdyBall")
                .setMessage("RowdyBall is a fun and interactive 8-ball game where you can ask any question and get an answer. Enjoy!")
                .setPositiveButton("OK", null)
                .show();
    }

    private void showLanguagePopup() {
        new AlertDialog.Builder(this)
                .setTitle("Select Language")
                .setItems(new String[]{"English", "Hindi", "Japanese", "Mandarin", "Spanish"}, (dialog, which) -> {
                    String selectedLanguage = "english_responses.txt";
                    switch (which) {
                        case 0:
                            selectedLanguage = "english_responses.txt";
                            break;
                        case 1:
                            selectedLanguage = "hindi_responses.txt";
                            break;
                        case 2:
                            selectedLanguage = "japanese_responses.txt";
                            break;
                        case 3:
                            selectedLanguage = "mandarin_responses.txt";
                            break;
                        case 4:
                            selectedLanguage = "spanish_responses.txt";
                            break;
                    }
                    // Store selected language in shared preferences
                    SharedPreferences prefs = getSharedPreferences("RowdyBallPrefs", MODE_PRIVATE);
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putString("selected_language", selectedLanguage);
                    editor.apply();
                    // Reload responses and display a new random response
                    loadResponses();
                    displayRandomResponse();
                })
                .setNegativeButton("Cancel", null)
                .show();
    }
    private void askAnotherQuestion() {
        Intent intent = new Intent(AnswerActivity.this, QuestionActivity.class);
        startActivity(intent);
    }
}