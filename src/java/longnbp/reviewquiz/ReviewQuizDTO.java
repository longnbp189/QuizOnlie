/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longnbp.reviewquiz;

import java.io.Serializable;

/**
 *
 * @author Admin
 */
public class ReviewQuizDTO implements Serializable{
    private int id, historyQuizId, questionId, answerId;
    public ReviewQuizDTO() {
    }

    public ReviewQuizDTO(int id, int historyQuizId, int questionId, int answerId) {
        this.id = id;
        this.historyQuizId = historyQuizId;
        this.questionId = questionId;
        this.answerId = answerId;
    }

    public ReviewQuizDTO(int id, int historyQuizId, int questionId) {
        this.id = id;
        this.historyQuizId = historyQuizId;
        this.questionId = questionId;
    }

    

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the historyQuizId
     */
    public int getHistoryQuizId() {
        return historyQuizId;
    }

    /**
     * @param historyQuizId the historyQuizId to set
     */
    public void setHistoryQuizId(int historyQuizId) {
        this.historyQuizId = historyQuizId;
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

    /**
     * @return the answerId
     */
    public int getAnswerId() {
        return answerId;
    }

    /**
     * @param answerId the answerId to set
     */
    public void setAnswerId(int answerId) {
        this.answerId = answerId;
    }

 
    
}
