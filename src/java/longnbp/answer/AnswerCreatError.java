/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longnbp.answer;

/**
 *
 * @author Admin
 */
public class AnswerCreatError {
    private String answer1ContentLengthErr;
    private String answer2ContentLengthErr;
    private String answer3ContentLengthErr;
    private String answerCorrectContentLengthErr;

    public AnswerCreatError() {
    }

    public AnswerCreatError(String answer1ContentLengthErr, String answer2ContentLengthErr, String answer3ContentLengthErr, String answerCorrectContentLengthErr) {
        this.answer1ContentLengthErr = answer1ContentLengthErr;
        this.answer2ContentLengthErr = answer2ContentLengthErr;
        this.answer3ContentLengthErr = answer3ContentLengthErr;
        this.answerCorrectContentLengthErr = answerCorrectContentLengthErr;
    }

    /**
     * @return the answer1ContentLengthErr
     */
    public String getAnswer1ContentLengthErr() {
        return answer1ContentLengthErr;
    }

    /**
     * @param answer1ContentLengthErr the answer1ContentLengthErr to set
     */
    public void setAnswer1ContentLengthErr(String answer1ContentLengthErr) {
        this.answer1ContentLengthErr = answer1ContentLengthErr;
    }

    /**
     * @return the answer2ContentLengthErr
     */
    public String getAnswer2ContentLengthErr() {
        return answer2ContentLengthErr;
    }

    /**
     * @param answer2ContentLengthErr the answer2ContentLengthErr to set
     */
    public void setAnswer2ContentLengthErr(String answer2ContentLengthErr) {
        this.answer2ContentLengthErr = answer2ContentLengthErr;
    }

    /**
     * @return the answer3ContentLengthErr
     */
    public String getAnswer3ContentLengthErr() {
        return answer3ContentLengthErr;
    }

    /**
     * @param answer3ContentLengthErr the answer3ContentLengthErr to set
     */
    public void setAnswer3ContentLengthErr(String answer3ContentLengthErr) {
        this.answer3ContentLengthErr = answer3ContentLengthErr;
    }

    /**
     * @return the answerCorrectContentLengthErr
     */
    public String getAnswerCorrectContentLengthErr() {
        return answerCorrectContentLengthErr;
    }

    /**
     * @param answerCorrectContentLengthErr the answerCorrectContentLengthErr to set
     */
    public void setAnswerCorrectContentLengthErr(String answerCorrectContentLengthErr) {
        this.answerCorrectContentLengthErr = answerCorrectContentLengthErr;
    }

   
}
