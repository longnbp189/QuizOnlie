/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longnbp.question;

/**
 *
 * @author Admin
 */
public class QuestionCreatError {
    private String questionContentLengthErr;

    public QuestionCreatError() {
    }

    public QuestionCreatError(String questionContentLengthErr) {
        this.questionContentLengthErr = questionContentLengthErr;
    }
    
    

    /**
     * @return the questionContentLengthErr
     */
    public String getQuestionContentLengthErr() {
        return questionContentLengthErr;
    }

    /**
     * @param questionContentLengthErr the questionContentLengthErr to set
     */
    public void setQuestionContentLengthErr(String questionContentLengthErr) {
        this.questionContentLengthErr = questionContentLengthErr;
    }
}
