package com.lidong.demo.view.model;

import java.util.List;

/**
 * Created by Administrator on 2016/5/3.
 */
public class Question {

    String question;
    List<Answer> mAnswer;

    public String getQuestion() {
        return question;
    }

    public List<Answer> getAnswer() {
        return mAnswer;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setAnswer(List<Answer> answer) {
        mAnswer = answer;
    }

    public static  class Answer{
        String answerCode;
        String answerMessage;

        public Answer() {
        }

        public Answer(String answerCode, String answerMessage) {
            this.answerCode = answerCode;
            this.answerMessage = answerMessage;
        }

        public String getAnswerCode() {
            return answerCode;
        }

        public void setAnswerCode(String answerCode) {
            this.answerCode = answerCode;
        }

        public String getAnswerMessage() {
            return answerMessage;
        }

        public void setAnswerMessage(String answerMessage) {
            this.answerMessage = answerMessage;
        }
    }
}
