/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longbp.historyquiz;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Admin
 */
public class HistoryQuizDTO implements Serializable {

    private String subjectId, email, time;
    private int id, correct_answer;
    private float grade;

    public HistoryQuizDTO() {
    }

    public HistoryQuizDTO(String subjectId, String email, String time, int id, int correct_answer, float grade) {
        this.subjectId = subjectId;
        this.email = email;
        this.time = time;
        this.id = id;
        this.correct_answer = correct_answer;
        this.grade = grade;
    }

    

    /**
     * @return the subjectId
     */
    public String getSubjectId() {
        return subjectId;
    }

    /**
     * @param subjectId the subjectId to set
     */
    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the time
     */
    public String getTime() {
        return time;
    }

    /**
     * @param time the time to set
     */
    public void setTime(String time) {
        this.time = time;
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
     * @return the correct_answer
     */
    public int getCorrect_answer() {
        return correct_answer;
    }

    /**
     * @param correct_answer the correct_answer to set
     */
    public void setCorrect_answer(int correct_answer) {
        this.correct_answer = correct_answer;
    }

    /**
     * @return the grade
     */
    public float getGrade() {
        return grade;
    }

    /**
     * @param grade the grade to set
     */
    public void setGrade(float grade) {
        this.grade = grade;
    }
    
}
