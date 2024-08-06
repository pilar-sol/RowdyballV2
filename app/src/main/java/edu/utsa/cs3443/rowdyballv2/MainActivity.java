package edu.utsa.cs3443.rowdyballv2;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
/**
 * The MainActivity class is the entry point of the RowdyBall application.
 * It initializes the main screen and handles interactions such as sliding to play,
 * playing music, and displaying information about the app.
 */
public class MainActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    private boolean isPlaying = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Initialize UI elements
        ImageView imageView = findViewById(R.id.imageView);
        TextView titleTextView = findViewById(R.id.titleTextView);
        TextView slideToPlayText = findViewById(R.id.slideToPlayText);
        SeekBar slideToPlaySeekBar = findViewById(R.id.slideToPlaySeekBar);
        Button musicButton = findViewById(R.id.musicButton);
        Button infoButton = findViewById(R.id.infoButton);

        slideToPlaySeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (progress == seekBar.getMax()) {
                    Intent intent = new Intent(MainActivity.this, QuestionActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // Do nothing
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // Do nothing
            }
        });

        musicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleMusic();
            }
        });

        infoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showInfoPopup();
            }
        });

        // Initialize the media player
        mediaPlayer = MediaPlayer.create(this, R.raw.project_sound);
    }

    private void toggleMusic() {
        if (isPlaying) {
            mediaPlayer.pause();
            isPlaying = false;
        } else {
            mediaPlayer.start();
            isPlaying = true;
        }
    }

    @Override
    protected void onDestroy() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
        super.onDestroy();
    }

    private void showInfoPopup() {
        new AlertDialog.Builder(this)
                .setTitle("About RowdyBall")
                .setMessage("RowdyBall is a fun and interactive 8-ball game where you can ask any question and get an answer. Enjoy!")
                .setPositiveButton("OK", null)
                .show();
    }
}