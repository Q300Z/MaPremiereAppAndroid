package fr.tbo.mapremierapp.controller;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import fr.tbo.mapremierapp.R;
import fr.tbo.mapremierapp.model.User;
public class MainActivity extends AppCompatActivity {

    private TextView mGreetingTextView;
    private EditText mNameEditText;
    private Button mPlayButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mGreetingTextView = findViewById(R.id.tMain);
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
        mPlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // The user just clicked
                Intent gameActivityIntent = new Intent(MainActivity.this, QuizActivity.class);
                startActivity(gameActivityIntent);
                //stock le nom du User
                mUser.setFirstName(mNameEditText.getText().toString());
            }
        });


    }


}