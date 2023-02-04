package fr.tbo.mapremierapp.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import fr.tbo.mapremierapp.R;
import fr.tbo.mapremierapp.model.User;

public class MainActivity extends AppCompatActivity {
    private EditText mNameEditText;
    private Button mPlayButton;
    private static final int GAME_ACTIVITY_REQUEST_CODE = 10;
    private static final String SHARED_PREF_USER_INFO = "SHARED_PREF_USER_INFO";
    private static final String SHARED_PREF_USER_INFO_NAME = "SHARED_PREF_USER_INFO_NAME";
    private static final String SHARED_PREF_USER_INFO_SCORE = "SHARED_PREF_USER_INFO_SCORE";


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (GAME_ACTIVITY_REQUEST_CODE == requestCode && RESULT_OK == resultCode){
            int score = data.getIntExtra(QuizActivity.BUNDLE_EXTRA_SCORE,0);
            getSharedPreferences(SHARED_PREF_USER_INFO, MODE_PRIVATE)
                    .edit()
                    .putInt(SHARED_PREF_USER_INFO_SCORE, score)
                    .apply();
            greetUser();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNameEditText = findViewById(R.id.zText);
        mPlayButton = findViewById(R.id.iButton);

        mPlayButton.setEnabled(false);



        User mUser = new User();
        mNameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                // This is where we'll check the user inputs
                mPlayButton.setEnabled(!s.toString().isEmpty());
            }
        });
        mPlayButton.setOnClickListener(view -> {
            //stock le nom du User
            mUser.setFirstName(mNameEditText.getText().toString());

            getSharedPreferences(SHARED_PREF_USER_INFO, MODE_PRIVATE)
                    .edit()
                    .putString(SHARED_PREF_USER_INFO_NAME, mUser.getFirstName())
                    .apply();

            // The user just clicked
            Intent gameActivityIntent = new Intent(MainActivity.this, QuizActivity.class);
            startActivityForResult(gameActivityIntent, GAME_ACTIVITY_REQUEST_CODE);
        });

    }
    private void greetUser() {
        String pseudo = getSharedPreferences(SHARED_PREF_USER_INFO, MODE_PRIVATE).getString(SHARED_PREF_USER_INFO_NAME, null);
        int score = getSharedPreferences(SHARED_PREF_USER_INFO, MODE_PRIVATE).getInt(SHARED_PREF_USER_INFO_SCORE, 0);
        TextView scoreBoard = findViewById(R.id.scoreBoard);
        if (pseudo != null) {
            if (score != -1) {
                scoreBoard.setText("Bonjour " + pseudo + " votre ancien score est " + score);
            } else {
                scoreBoard.setText("Bonjour " + pseudo);
            }
        }
    }
}