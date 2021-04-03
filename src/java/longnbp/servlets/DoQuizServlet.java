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
import longnbp.answer.AnswerDAO;
import longnbp.question.QuestionDTO;
import longnbp.question.QuestionDAO;

/**
 *
 * @author Admin
 */
@WebServlet(name = "DoQuizServlet", urlPatterns = {"/DoQuizServlet"})
public class DoQuizServlet extends HttpServlet {

    private final String TAKE_QUIZ_PAGE = "SelectQuestionServlet";
    private final String GET_SUBJECT = "GetSubjectServlet";

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
        String url = TAKE_QUIZ_PAGE;
        try {
            HttpSession s = request.getSession();
            String subjectId = request.getParameter("cbSubject");
            if (subjectId.equals("")) {
                url = GET_SUBJECT;
                request.setAttribute("SUB_ERR", "Please choose subject name");
            } else {
                QuestionDAO qDAO = new QuestionDAO();
                AnswerDAO aDAO = new AnswerDAO();
                List<QuestionDTO> qList = qDAO.getQuestionListForStudent(subjectId);
                if (qList != null) {
                    for (QuestionDTO questionDTO : qList) {
                        questionDTO.setAnswers(aDAO.getAnswerList(questionDTO.getQuestionId()));
                    }
                    s.setAttribute("QUESTION", qList.get(0));
                    s.setAttribute("NUM", 1);
                }
                SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                String date = format.format(new Date());
                s.setAttribute("TIME", date);
                s.setAttribute("QUESTION_LIST", qList);
                s.setAttribute("SUBJECT", subjectId);
            }
        } catch (SQLException ex) {
            log("DoQuizServlet_SQL " + ex.getMessage());
        } catch (NamingException ex) {
            log("DoQuizServlet_Naming " + ex.getMessage());
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
