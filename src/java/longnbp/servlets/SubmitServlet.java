/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longnbp.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import longbp.historyquiz.HistoryQuizDAO;
import longbp.historyquiz.HistoryQuizDTO;
import longnbp.account.AccountDTO;
import longnbp.answer.AnswerDAO;
import longnbp.question.QuestionDTO;
import longnbp.reviewquiz.ReviewQuizDAO;
import longnbp.reviewquiz.ReviewQuizDTO;
import longnbp.subject.SubjectDAO;

/**
 *
 * @author Admin
 */
@WebServlet(name = "SubmitServlet", urlPatterns = {"/SubmitServlet"})
public class SubmitServlet extends HttpServlet {

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
        String url = "quizResult.jsp";
        try {
            HttpSession s = request.getSession();
            AccountDTO aDTO = (AccountDTO) s.getAttribute("ACCOUNT_DTO");
            String subjectId = (String) s.getAttribute("SUBJECT");

            AnswerDAO aDAO = new AnswerDAO();
            SubjectDAO sDAO = new SubjectDAO();
            HistoryQuizDAO hDAO = new HistoryQuizDAO();
            ReviewQuizDAO rqDAO = new ReviewQuizDAO();

            List<QuestionDTO> qList = (List<QuestionDTO>) s.getAttribute("QUESTION_LIST");
            int correct = 0;
            for (QuestionDTO questionDTO : qList) {
                if (questionDTO.getStudentAnswer() != -1) {
                    if (aDAO.isTrueAnswer(questionDTO.getStudentAnswer())) {
                        correct++;
                    }
                }
            }

            hDAO.addResultQuizDetail(new HistoryQuizDTO(subjectId, aDTO.getEmail(), "", 0, correct, correct));
            int historyId = hDAO.getHistoryIdMax(aDTO.getEmail());
            for (QuestionDTO questionDTO : qList) {
                if (questionDTO.getStudentAnswer() != -1) {
                    rqDAO.insertReviewQuiz(new ReviewQuizDTO(0, historyId, questionDTO.getQuestionId(), questionDTO.getStudentAnswer()));
                } else {
                    rqDAO.insertReviewQuizWithoutAnswer(new ReviewQuizDTO(0, historyId, questionDTO.getQuestionId()));
                }

            }

            String subject = sDAO.getSubjectById(subjectId);
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            String createDate = format.format(new Date());

            request.setAttribute("SUBJECT_NAME", subject);
            request.setAttribute("TIME_SUBMIT", createDate);
            request.setAttribute("CORRECT", correct);
        } catch (SQLException ex) {
            log("SubmitServlet_SQL " + ex.getMessage());
        } catch (NamingException ex) {
            log("SubmitServlet_Naming " + ex.getMessage());
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
