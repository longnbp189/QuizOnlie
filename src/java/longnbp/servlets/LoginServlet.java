/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longnbp.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import longnbp.account.AccountDAO;
import longnbp.account.AccountDTO;
import longnbp.utilities.PasswordEncryption;

/**
 *
 * @author Admin
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    private final String Login_PAGE = "try";
    private final String HOME_PAGE = "getSubject";
    private final String SEARCH_PAGE = "search";

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
        String url = Login_PAGE;
        String email = request.getParameter("txtEmail");
        String password = request.getParameter("txtPassword");
        PasswordEncryption enc = new PasswordEncryption();
        password = enc.toSHAString(password);
        AccountDAO dao = new AccountDAO();
        HttpSession session = request.getSession();
        try {
            boolean result = dao.checkLogin(email, password);
            if (result) {
                AccountDTO dto = dao.getAccountDetail(email);
                url = HOME_PAGE;
                if (dto.isRole()) {
                    url = SEARCH_PAGE;
                }
                session.setAttribute("ACCOUNT_DTO", dto);
                session.removeAttribute("LOGIN_ERROR");
            } else {
                session.setAttribute("LOGIN_ERROR", "Invaid email or password");
            }

        } catch (NamingException ex) {
            log("LoginServlet_Naming " + ex.getMessage());
        } catch (SQLException ex) {
            log("LoginServlet_SQL " + ex.getMessage());
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
