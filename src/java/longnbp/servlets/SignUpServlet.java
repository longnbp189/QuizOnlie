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
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import longnbp.account.AccountCreatError;
import longnbp.account.AccountDAO;
import longnbp.utilities.PasswordEncryption;

/**
 *
 * @author Admin
 */
@WebServlet(name = "SignUpServlet", urlPatterns = {"/SignUpServlet"})
public class SignUpServlet extends HttpServlet {

    private final String INVALID_PAGE = "signUp.jsp";
    private final String LOGIN_PAGE = "login.jsp";

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
        String url = INVALID_PAGE;
        try {
            String email = request.getParameter("txtEmail");
            String password = request.getParameter("txtPassword");
            String confirm = request.getParameter("txtConfirm");
            String name = request.getParameter("txtName");
            AccountCreatError errors = new AccountCreatError();
            HttpSession s = request.getSession();
            s.removeAttribute("LOGIN_ERROR");
            try {
                if (request.getParameter("txtEmail") != null
                        && request.getParameter("txtPassword") != null
                        && request.getParameter("txtConfirm") != null
                        && request.getParameter("txtName") != null) {
                    boolean foundErr = false;
                    if (email.trim().length() < 6 || email.trim().length() > 50) {
                        foundErr = true;
                        errors.setEmailLengthError("Length must from 6 to 50");
                    } else if (!email.trim().matches("\\w+@\\w+[.]\\w+")) {
                        foundErr = true;
                        errors.setEmailFormErr("Email contain only one “@” character");

                    }
                    if (password.trim().length() < 6 || password.trim().length() > 50) {
                        foundErr = true;
                        errors.setPasswordLengthError("Length must from 6 to 50");
                    } else if (!password.equals(confirm)) {
                        foundErr = true;
                        errors.setConfirmPasswordError("Not match with password");
                    }
                    if (name.trim().length() < 2 || name.trim().length() > 50) {
                        foundErr = true;
                        errors.setNameLengthError("Length must from 2 to 50");
                    }
                    if (foundErr) {
                        request.setAttribute("CREATEERROR", errors);
                    } else {
                        PasswordEncryption enc = new PasswordEncryption();
                        String pass = enc.toSHAString(password);
                        AccountDAO dao = new AccountDAO();
                        boolean result = dao.createAccount(email, name, pass, false, false);
                        if (result) {
                            url = LOGIN_PAGE;
                        }
                    }
                }
            } catch (SQLException e) {
                String errMgs = e.getMessage();
                log("SignUpServlet_SQL " + errMgs);
                e.printStackTrace();
                if (errMgs.contains("duplicate")) {
                    errors.setEmailExistError(email + " is existed");
                    request.setAttribute("CREATEERROR", errors);
                }
            } catch (NamingException ex) {
                log("SignUpServlet_Naming " + ex.getMessage());
            }
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
