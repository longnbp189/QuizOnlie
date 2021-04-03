/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longnbp.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import longnbp.answer.AnswerCreatError;
import longnbp.answer.AnswerDAO;
import longnbp.answer.AnswerDTO;
import longnbp.question.QuestionCreatError;
import longnbp.question.QuestionDAO;
import longnbp.question.QuestionDTO;
import longnbp.reviewquiz.ReviewQuizDAO;
import longnbp.subject.SubjectDAO;
import longnbp.subject.SubjectDTO;

/**
 *
 * @author Admin
 */
@WebServlet(name = "UpdateQuestionServlet", urlPatterns = {"/UpdateQuestionServlet"})
public class UpdateQuestionServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String url = "updateQuestion.jsp";
        try {
            boolean check = false;
            QuestionCreatError qErr = new QuestionCreatError();
            AnswerCreatError aErr = new AnswerCreatError();
            int questionId = Integer.parseInt(request.getParameter("txtQuestionId"));
            QuestionDAO qDAO = new QuestionDAO();
            SubjectDAO sDAO = new SubjectDAO();
            AnswerDAO aDAO = new AnswerDAO();
            ReviewQuizDAO rqDAO = new ReviewQuizDAO();

            String name = request.getParameter("txtName");
            request.setAttribute("SEARCH_NAME", name);

            String sta = request.getParameter("cbStatus");
            request.setAttribute("SEARCH_STATUS", sta);

            String subjectId = request.getParameter("cbSubject");
            request.setAttribute("SEARCH_SUBJECT", subjectId);

            String pageNum = request.getParameter("txtPage");
            request.setAttribute("SEARCH_PAGE", pageNum);

            List<SubjectDTO> sList = sDAO.getSubjectList();
            request.setAttribute("SUBJECT_LIST", sList);

            QuestionDTO qDTO = qDAO.getQuestionById(questionId);
            request.setAttribute("QUESTION", qDTO);
            List<AnswerDTO> aDTOs = aDAO.getAnswerListFalse(questionId);
            AnswerDTO aDTO = aDAO.getAnswerTrue(questionId);
            for (int i = 0; i < aDTOs.size(); i++) {
                request.setAttribute("ANSWER" + i, aDTOs.get(i));
            }
            request.setAttribute("ANSWER_TRUE", aDTO);
            request.setAttribute("QUESTION_ID", questionId);

            if (!rqDAO.isTest(questionId)) {
                if (request.getParameter("btAction1") != null) {
                    String questionContent = "";
                    if (request.getParameter("txtQuestionContent") != null) {
                        questionContent = request.getParameter("txtQuestionContent");
                        if (questionContent.trim().equals("")) {
                            check = true;
                            qErr.setQuestionContentLengthErr("Question content can not empty");
                        }
                    }

                    String answer1 = "";
                    String answer2 = "";
                    String answer3 = "";
                    String correctAnswer = "";

                    if (request.getParameter("txtAnswer1") != null) {
                        answer1 = request.getParameter("txtAnswer1").trim();
                        if (answer1.equals("")) {
                            check = true;
                            aErr.setAnswer1ContentLengthErr("Answer content can not empty");
                        }
                    }
                    int answer1Id = Integer.parseInt(request.getParameter("txtAnswer1Id"));

                    if (request.getParameter("txtAnswer2") != null) {
                        answer2 = request.getParameter("txtAnswer2").trim();
                        if (answer2.equals("")) {
                            check = true;
                            aErr.setAnswer2ContentLengthErr("Answer content can not empty");
                        }
                    }
                    int answer2Id = Integer.parseInt(request.getParameter("txtAnswer2Id"));

                    if (request.getParameter("txtAnswer3") != null) {
                        answer3 = request.getParameter("txtAnswer3").trim();
                        if (answer3.equals("")) {
                            check = true;
                            aErr.setAnswer3ContentLengthErr("Answer content can not empty");
                        }
                    }
                    int answer3Id = Integer.parseInt(request.getParameter("txtAnswer3Id"));

                    if (request.getParameter("txtCorrectAnswer") != null) {
                        correctAnswer = request.getParameter("txtCorrectAnswer").trim();
                        if (correctAnswer.equals("")) {
                            check = true;
                            aErr.setAnswerCorrectContentLengthErr("Answer content can not empty");
                        }
                    }
                     if (!check) {
                    if (answer1.equals(answer2) || answer1.equals(answer3) || answer1.equals(correctAnswer)) {
                        check = true;
                        aErr.setAnswer1ContentLengthErr("answer1 must be unique");
                    }
                    if (answer2.equals(answer1) || answer2.equals(answer3) || answer2.equals(correctAnswer)) {
                        check = true;
                        aErr.setAnswer2ContentLengthErr("answer2 must be unique");
                    }
                    if (answer3.equals(answer2) || answer3.equals(answer1) || answer3.equals(correctAnswer)) {
                        check = true;
                        aErr.setAnswer3ContentLengthErr("answer3 must be unique");
                    }
                    if (correctAnswer.equals(answer2) || correctAnswer.equals(answer3) || correctAnswer.equals(answer1)) {
                        check = true;
                        aErr.setAnswerCorrectContentLengthErr("correctAnswer must be unique");
                    }
                }
                    int correctAnswerId = Integer.parseInt(request.getParameter("txtCorrectAnswerId"));

                    String staUpdate = request.getParameter("cbStatusUpdate");
                    boolean statusUpdate = false;
                    if (staUpdate.equals("true")) {
                        statusUpdate = true;
                    }

                    String subjectIdUpdate = request.getParameter("cbSubjectUpdate");

                    if (!check) {
                        qDAO.updateQuestion(questionContent, questionId, subjectIdUpdate, statusUpdate);
                        aDAO.updateAnswer(correctAnswerId, correctAnswer, true);
                        aDAO.updateAnswer(answer1Id, answer1, false);
                        aDAO.updateAnswer(answer2Id, answer2, false);
                        aDAO.updateAnswer(answer3Id, answer3, false);
                        url = "SearchQuestionServlet?txtName=" + name + "&cbStatus=" + sta + "&cbSubject=" + subjectId + "&btnPage=" + pageNum;
                    } else {
                        request.setAttribute("QERR", qErr);
                        request.setAttribute("AERR", aErr);
                    }
                }
            } else {
                url = "SearchQuestionServlet?txtName=" + name + "&cbStatus=" + sta + "&cbSubject=" + subjectId + "&btnPage=" + pageNum;
                request.setAttribute("TEST_ERR", "Question is used");
            }

        } catch (SQLException ex) {
            log("UpdateQuestionServlet_SQL " + ex.getMessage());
        } catch (NamingException ex) {
            log("UpdateQuestionServlet_Naming " + ex.getMessage());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
            out.close();
        }
    }

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
