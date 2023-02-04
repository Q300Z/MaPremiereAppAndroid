package fr.tbo.mapremierapp.model;

import java.util.Collections;
import java.util.List;

public class QuestionBank {
    private final List<Questions> mQuestionList;
    private int mNextQuestionIndex = 0;

    public QuestionBank(List<Questions> questionList) {
        // Shuffle the question list before storing it
        mQuestionList = questionList;
        Collections.shuffle(mQuestionList);
    }
    public Questions getCurrentQuestion() {
        return mQuestionList.get(mNextQuestionIndex);
    }

    public Questions getNextQuestion() {
        // Loop over the questions and return a new one at each call
        mNextQuestionIndex++;
        return getCurrentQuestion();
    }
}
