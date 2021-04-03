/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longnbp.reviewquiz;

import java.io.Serializable;
import java.util.List;
import longnbp.answer.AnswerDTO;

/**
 *
 * @author Admin
 */
public class ReviewQuizView implements Serializable{
    private String questionContent, answerContent, correctAnswer;
    private boolean status = false;
    private int questionId;

    public ReviewQuizView() {
    }

    private List<AnswerDTO> answers;

    public ReviewQuizView(String questionContent, String answerContent, String correctAnswer, int questionId, boolean status) {
        this.questionContent = questionContent;
        this.answerContent = answerContent;
        this.correctAnswer = correctAnswer;
        this.questionId = questionId;
        this.status = status;
    }
    


    /**
     * @return the questionContent
     */
    public String getQuestionContent() {
        return questionContent;
    }

    /**
     * @param questionContent the questionContent to set
     */
    public void setQuestionContent(String questionContent) {
        this.questionContent = questionContent;
    }

    /**
     * @return the answerContent
     */
    public String getAnswerContent() {
        return answerContent;
    }

    /**
     * @param answerContent the answerContent to set
     */
    public void setAnswerContent(String answerContent) {
        this.answerContent = answerContent;
    }

    /**
     * @return the status
     */
    public boolean isStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(boolean status) {
        this.status = status;
    }

    /**
     * @return the correctAnswer
     */
    public String getCorrectAnswer() {
        return correctAnswer;
    }

    /**
     * @param correctAnswer the correctAnswer to set
     */
    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    /**
     * @return the answers
     */
    public List<AnswerDTO> getAnswers() {
        return answers;
    }

    /**
     * @param answers the answers to set
     */
    public void setAnswers(List<AnswerDTO> answers) {
        this.answers = answers;
    }

    /**
     * @return the questionId
     */
    public int getQuestionId() {
        return questionId;
    }

    /**
     * @param questionId the questionId to set
     */
    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }
    
    
}
