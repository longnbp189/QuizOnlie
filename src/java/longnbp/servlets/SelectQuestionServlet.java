/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longnbp.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import longnbp.question.QuestionDTO;

/**
 *
 * @author Admin
 */
@WebServlet(name = "SelectQuestionServlet", urlPatterns = {"/SelectQuestionServlet"})
public class SelectQuestionServlet extends HttpServlet {

    private static String TAKE_QUIZ_PAGE = "take-quiz";

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
            if (request.getParameter("btnNum") != null) {
                String num = request.getParameter("btnNum");
                String answer;
                String question = request.getParameter("txtQuestionId");
                HttpSession s = request.getSession();
                int ans = 0, ques = 0;
                if (request.getParameter("radAnswer") != null) {
                    answer = request.getParameter("radAnswer");
                    try {
                        ques = Integer.parseInt(question);
                        ans = Integer.parseInt(answer);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                List<QuestionDTO> qList = (List<QuestionDTO>) s.getAttribute("QUESTION_LIST");
                for (QuestionDTO questionDTO : qList) {
                    if (questionDTO.getQuestionId() == ques) {
                        questionDTO.setStudentAnswer(ans);
                    }
                }
                int number = (int) s.getAttribute("NUM");
                try {
                    number = Integer.parseInt(num);
                } catch (NumberFormatException e) {
                    if (num.equals("Next")) {
                        number++;
                    } else {
                        number--;
                    }
                }
                s.setAttribute("QUESTION", qList.get(number - 1));
                s.setAttribute("QUESTION_LIST", qList);
                s.setAttribute("NUM", number);
            }
        } finally {
            response.sendRedirect(url);
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
