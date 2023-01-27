package fr.tbo.mapremierapp.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

import fr.tbo.mapremierapp.R;
import fr.tbo.mapremierapp.model.QuestionBank;
import fr.tbo.mapremierapp.model.Questions;

public class QuizActivity extends AppCompatActivity implements View.OnClickListener {
    Button mGameButton1;
    Button mGameButton2;
    Button mGameButton3;
    Button mGameButton4;
    TextView mTextViewQuestion;
    private final QuestionBank mQuestionBank = generateQuestionBank();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        mTextViewQuestion = findViewById(R.id.game_activity_textview_question);
        mGameButton1 = findViewById(R.id.game_activity_button_1);
        mGameButton2 = findViewById(R.id.game_activity_button_2);
        mGameButton3 = findViewById(R.id.game_activity_button_3);
        mGameButton4 = findViewById(R.id.game_activity_button_4);

        mGameButton1.setOnClickListener(this);
        mGameButton2.setOnClickListener(this);
        mGameButton3.setOnClickListener(this);
        mGameButton4.setOnClickListener(this);

        displayQuestion(mQuestionBank.getCurrentQuestion());
    }
    private QuestionBank generateQuestionBank(){
        Questions question1 = new Questions(
                "Who is the creator of Android?",
                Arrays.asList(
                        "Andy Rubin",
                        "Steve Wozniak",
                        "Jake Wharton",
                        "Paul Smith"
                ),
                0
        );

        Questions question2 = new Questions(
                "When did the first man land on the moon?",
                Arrays.asList(
                        "1958",
                        "1962",
                        "1967",
                        "1969"
                ),
                3
        );

        Questions question3 = new Questions(
                "What is the house number of The Simpsons?",
                Arrays.asList(
                        "42",
                        "101",
                        "666",
                        "742"
                ),
                3
        );
        return new QuestionBank(Arrays.asList(question1, question2, question3));
    }
    private void displayQuestion(final Questions question) {
    // Set the text for the question text view and the four buttons
        mTextViewQuestion.setText(question.getQuestion());
        List<String> answers = question.getChoiceList();
        mGameButton1.setText(answers.get(0));
        mGameButton2.setText(answers.get(1));
        mGameButton3.setText(answers.get(2));
        mGameButton4.setText(answers.get(3));
    }
    @Override
    public void onClick(View v) {
        int index;
        if (v == mGameButton1) {
            index = 0;
            checkSuccess(index, mQuestionBank.getCurrentQuestion().getAnswerIndex());

        } else if (v == mGameButton2) {
            index = 1;
            checkSuccess(index, mQuestionBank.getCurrentQuestion().getAnswerIndex());

        } else if (v == mGameButton3) {
            index = 2;
            checkSuccess(index, mQuestionBank.getCurrentQuestion().getAnswerIndex());

        } else if (v == mGameButton4) {
            index = 3;
            checkSuccess(index, mQuestionBank.getCurrentQuestion().getAnswerIndex());

        } else {
            throw new IllegalStateException("Unknown clicked view : " + v);
        }
    }
    private void checkSuccess(int answer, int questionIndex){
        if (answer == questionIndex) {
            Toast.makeText(this, "Correct!",Toast.LENGTH_SHORT).show();
        }
    }
}