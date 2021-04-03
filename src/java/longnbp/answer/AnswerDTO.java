/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longnbp.answer;

import java.io.Serializable;

/**
 *
 * @author Admin
 */
public class AnswerDTO implements  Serializable{
    private int answerId, questionId;
    private String answer_content;
    private boolean status;

    public AnswerDTO() {
    }

    public AnswerDTO(int answerId, int questionId, String answer_content, boolean status) {
        this.answerId = answerId;
        this.questionId = questionId;
        this.answer_content = answer_content;
        this.status = status;
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
     * @return the answer_content
     */
    public String getAnswer_content() {
        return answer_content;
    }

    /**
     * @param answer_content the answer_content to set
     */
    public void setAnswer_content(String answer_content) {
        this.answer_content = answer_content;
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

   
    
}
