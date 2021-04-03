/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longnbp.question;

import java.io.Serializable;
import java.util.List;
import longnbp.answer.AnswerDTO;

/**
 *
 * @author Admin
 */
public class QuestionDTO implements Serializable{
    private int questionId;
    private String question_contect, subjectId;
    private String createDate;
    private boolean status;
    
    private int studentAnswer = -1;
    
    private List<AnswerDTO> answers;

    public QuestionDTO() {
    }

    public QuestionDTO(int questionId, String question_contect, String subjectId, String createDate, boolean status) {
        this.questionId = questionId;
        this.question_contect = question_contect;
        this.subjectId = subjectId;
        this.createDate = createDate;
        this.status = status;
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
     * @return the question_contect
     */
    public String getQuestion_contect() {
        return question_contect;
    }

    /**
     * @param question_contect the question_contect to set
     */
    public void setQuestion_contect(String question_contect) {
        this.question_contect = question_contect;
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
     * @return the createDate
     */
    public String getCreateDate() {
        return createDate;
    }

    /**
     * @param createDate the createDate to set
     */
    public void setCreateDate(String createDate) {
        this.createDate = createDate;
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
     * @return the studentAnswer
     */
    public int getStudentAnswer() {
        return studentAnswer;
    }

    /**
     * @param studentAnswer the studentAnswer to set
     */
    public void setStudentAnswer(int studentAnswer) {
        this.studentAnswer = studentAnswer;
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

    
    
}
