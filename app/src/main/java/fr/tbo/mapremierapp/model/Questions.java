package fr.tbo.mapremierapp.model;

import java.util.List;

public class Questions {
    private final String mQuestion;
    private final List<String> mChoiceList;
    private final int mAnswerIndex;

    public Questions(String question, List<String> choiceList, int answerIndex) {
        mQuestion = question;
        mChoiceList = choiceList;
        mAnswerIndex = answerIndex;
    }

    public String getQuestion() {
        return mQuestion;
    }

    public List<String> getChoiceList() {
        return mChoiceList;
    }

    public int getAnswerIndex() {
        return mAnswerIndex;
    }
}
