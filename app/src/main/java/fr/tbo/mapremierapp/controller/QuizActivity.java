package fr.tbo.mapremierapp.controller;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
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
    private int mRemainingQuestionCount;
    private int mScore = 0;
    public static final String BUNDLE_EXTRA_SCORE = "BUNDLE_EXTRA_SCORE";
    private boolean mEnableTouchEvents;

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return mEnableTouchEvents && super.dispatchTouchEvent(ev);
    }

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

        mEnableTouchEvents = true;

        mRemainingQuestionCount = 10;

        displayQuestion(mQuestionBank.getCurrentQuestion());
    }

    private QuestionBank generateQuestionBank() {
        Questions question1 = new Questions(
                "Quel est le plus grand pays du monde en termes de superficie ?",
                Arrays.asList(
                        "Chine",
                        "Russie",
                        "États-Unis",
                        "Canada"
                ),
                1
        );

        Questions question2 = new Questions(
                "Qui a peint la Joconde ?",
                Arrays.asList(
                        "Michel-Ange",
                        "Raphaël",
                        "Leonardo da Vinci",
                        "Donatello"
                ),
                2
        );

        Questions question3 = new Questions(
                "Quel est le plus haut sommet d'Europe ?",
                Arrays.asList(
                        "Mont Blanc",
                        "Elbrus",
                        "Mont Viso",
                        "Monte Rosa"
                ),
                0
        );

        Questions question4 = new Questions(
                "Quelle est la capitale de l'Australie ?",
                Arrays.asList(
                        "Sydney",
                        "Melbourne",
                        "Canberra",
                        "Brisbane"
                ),
                2
        );

        Questions question5 = new Questions(
                "Quel est le nom de la devise de l'Union européenne ?",
                Arrays.asList(
                        "Dollar",
                        "Livre Sterling",
                        "Franc Suisse",
                        "Euro"
                ),
                3
        );

        Questions question6 = new Questions(
                "Qui a écrit 'Les Misérables' ?",
                Arrays.asList(
                        "Victor Hugo",
                        "Charles Dickens",
                        "Alexandre Dumas",
                        "Gustave Flaubert"
                ),
                0
        );

        Questions question7 = new Questions(
                "Quel est le plus long fleuve d'Afrique ?",
                Arrays.asList(
                        "Nil",
                        "Congo",
                        "Niger",
                        "Zambèze"
                ),
                0
        );

        Questions question8 = new Questions(
                "Quelle est la plus grande océan de la Terre ?",
                Arrays.asList(
                        "Atlantique",
                        "Pacifique",
                        "Indien",
                        "Arctique"
                ),
                1
        );

        Questions question9 = new Questions(
                "Quel est le nom du célèbre scientifique qui a développé la théorie de la relativité ?",
                Arrays.asList(
                        "Albert Einstein",
                        "Stephen Hawking",
                        "Isaac Newton",
                        "Galileo Galilei"
                ),
                0
        );

        Questions question10 = new Questions(
                "Quelle est la plus grande religion du monde ?",
                Arrays.asList(
                        "Islam",
                        "Hinduisme",
                        "Christianisme",
                        "Bouddhisme"
                ),
                2
        );
        return new QuestionBank(Arrays.asList(question1, question2, question3, question4, question5, question6, question7, question8, question9, question10));
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
        mEnableTouchEvents =false;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mRemainingQuestionCount--;

                if (mRemainingQuestionCount > 0) {
                    displayQuestion(mQuestionBank.getNextQuestion());
                } else {
                    endGame();
                }
                mEnableTouchEvents = true;
            }
        }, 1000);

    }

    private void endGame() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Well done!")
                .setMessage("Ton score est de " + mScore)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent();
                        intent.putExtra(BUNDLE_EXTRA_SCORE, mScore);
                        setResult(RESULT_OK, intent);
                        finish();
                    }
                })
                .create()
                .show();
    }

    private void checkSuccess(int answer, int questionIndex) {
        if (answer == questionIndex) {
            Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show();
            mScore++;
        } else {
            Toast.makeText(this, "Incorrect!", Toast.LENGTH_SHORT).show();
        }
    }
}