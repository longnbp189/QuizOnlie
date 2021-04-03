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
import longnbp.answer.AnswerDAO;
import longnbp.question.QuestionDTO;
import longnbp.question.QuestionDAO;
import longnbp.subject.SubjectDAO;
import longnbp.subject.SubjectDTO;

/**
 *
 * @author Admin
 */
@WebServlet(name = "SearchQuestionServlet", urlPatterns = {"/SearchQuestionServlet"})
public class SearchQuestionServlet extends HttpServlet {

    private final String SEARCH_PAGE = "search.jsp";

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
        String url = SEARCH_PAGE;
        try {

            int page = 1;
            if (request.getParameter("btnPage") != null) {
                if (!request.getParameter("btnPage").equals("")) {
                    page = Integer.parseInt(request.getParameter("btnPage"));
                }
            }
            request.setAttribute("PAGE_NUM", page);

            SubjectDAO sDAO = new SubjectDAO();
            List<SubjectDTO> sList = sDAO.getSubjectList();
            request.setAttribute("SUBJECT_LIST", sList);

            String name = "";
            if (request.getParameter("txtName") != null) {
                name = request.getParameter("txtName");
            }

            boolean status = true;
            if (request.getParameter("cbStatus") != null) {
                if (request.getParameter("cbStatus").equals("false")) {
                    status = false;
                }
            }

            String subject = "";
            if (request.getParameter("cbSubject") != null) {
                subject = request.getParameter("cbSubject");
            }

            request.setAttribute("SELECTED_SUBJECT", subject);
            request.setAttribute("SELECTED_STATUS", status);

            QuestionDAO qDAO = new QuestionDAO();
            AnswerDAO aDAO = new AnswerDAO();
            List<QuestionDTO> list;

            if (subject.equals("")) {
                list = null;
            } else {
                list = qDAO.getQuestionListForAdmin(name, subject, status, page);
                if (list != null) {
                    request.setAttribute("PAGE_COUNT", qDAO.pageCountForAdmin(name, subject, status));
                    for (QuestionDTO questionDTO : list) {
                        questionDTO.setAnswers(aDAO.getAnswerList(questionDTO.getQuestionId()));
                    }
                }
            }

            request.setAttribute("QUESTION_LIST", list);

        } catch (SQLException ex) {
            log("SearchQuestionServlet " + ex.getMessage());
        } catch (NamingException ex) {
            log("SearchQuestionServlet_Naming " + ex.getMessage());
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
