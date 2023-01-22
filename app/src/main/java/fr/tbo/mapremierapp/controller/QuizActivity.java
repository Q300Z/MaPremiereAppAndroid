package fr.tbo.mapremierapp.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import fr.tbo.mapremierapp.R;

public class QuizActivity extends AppCompatActivity {
    Button mGameButton1;
    Button mGameButton2;
    Button mGameButton3;
    Button mGameButton4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        mGameButton1 = findViewById(R.id.game_activity_button_1);
        mGameButton2 = findViewById(R.id.game_activity_button_2);
        mGameButton3 = findViewById(R.id.game_activity_button_3);
        mGameButton4 = findViewById(R.id.game_activity_button_4);
    }
}