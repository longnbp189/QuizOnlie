/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longnbp.question;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.naming.NamingException;
import longnbp.utilities.DBHelper;

/**
 *
 * @author Admin
 */
public class QuestionDAO implements Serializable {

    public int pageCountForAdmin(String search, String subject, boolean status) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBHelper.getConnection();
            if (con != null) {
                String sql = "SELECT count(q.questionId) AS row "
                        + "FROM question q JOIN subject s ON q.subjectId = s.subjectId "
                        + "WHERE q.question_content LIKE ? AND s.subjectId = ?  AND q.status = ? ";
                ps = con.prepareStatement(sql);
                ps.setString(1, "%" + search + "%");
                ps.setString(2, subject);
                ps.setBoolean(3, status);
                rs = ps.executeQuery();
                if (rs.next()) {
                    int count = (rs.getInt("row") - 1) / 20;
                    return count + 1;
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return 0;
    }

    public List<QuestionDTO> getQuestionListForAdmin(String search, String subject, boolean status, int page) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<QuestionDTO> list = null;
        int num = (page - 1) * 20;
        try {
            con = DBHelper.getConnection();
            String sql = "SELECT q.questionId, q.question_content, q.createDate, s.subjectName "
                    + "FROM question q JOIN subject s ON q.subjectId = s.subjectId "
                    + "WHERE q.question_content LIKE ? AND s.subjectId = ?  AND q.status = ? "
                    + "ORDER BY q.question_content "
                    + "OFFSET ? ROWS "
                    + "FETCH NEXT 20 ROWS ONLY";
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + search + "%");
            ps.setString(2, subject);
            ps.setBoolean(3, status);
            ps.setInt(4, num);
            rs = ps.executeQuery();
            while (rs.next()) {
                if (list == null) {
                    list = new ArrayList<>();
                }
                int id = rs.getInt("questionId");
                String question_content = rs.getString("question_content");
                Timestamp date = rs.getTimestamp("createDate");
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                String createDate = format.format(date);
                String subjectName = rs.getString("subjectName");
                list.add(new QuestionDTO(id, question_content, subjectName, createDate, status));
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return list;
    }

    public List<QuestionDTO> getQuestionListForStudent(String subjectId) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<QuestionDTO> list = null;
        try {
            con = DBHelper.getConnection();
            String sql = "SELECT questionId, question_content "
                    + "FROM question "
                    + "WHERE subjectId = ? AND status = 1 "
                    + "ORDER BY NEWID() "
                    + "OFFSET 0 ROWS "
                    + "FETCH NEXT 10 ROWS ONLY";
            ps = con.prepareStatement(sql);
            ps.setString(1, subjectId);
            rs = ps.executeQuery();
            while (rs.next()) {
                if (list == null) {
                    list = new ArrayList<>();
                }
                int id = rs.getInt("questionId");
                String question_content = rs.getString("question_content");
                list.add(new QuestionDTO(id, question_content, "", "", true));
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return list;
    }
    
    public void deleteQuestion(String questionId) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBHelper.getConnection();
            String sql = "UPDATE question "
                    + "SET status = 0 "
                    + "WHERE questionId = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, questionId);
            ps.executeUpdate();
        } finally {
           
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }
    
    public void updateQuestion(String questionContent, int questionId, String subjectId, boolean status) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBHelper.getConnection();
            String sql = "UPDATE question "
                    + "SET question_content = ?, subjectId = ?, status = ? "
                    + "WHERE questionId = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(4, questionId);
            ps.setString(2, subjectId);
            ps.setString(1, questionContent);
            ps.setBoolean(3, status);
            ps.executeUpdate();
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }
    
     public void insertQuestion(String questionContent, String subjectId, boolean status) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBHelper.getConnection();
            String sql = "INSERT INTO question (question_content, subjectId, status, createDate) "
                    + "VALUES (?,?,?, ?) ";
            ps = con.prepareStatement(sql);
            Timestamp date = new Timestamp((new Date()).getTime());
            ps.setString(1, questionContent);
            ps.setString(2, subjectId);
            ps.setBoolean(3, status);
            ps.setTimestamp(4, date);
            ps.executeUpdate();
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }
     
     public int getQuestionIdMax() throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBHelper.getConnection();
            String sql = "SELECT TOP 1 questionId "
                    + "FROM question "
                    + "ORDER BY questionId DESC";

            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("questionId");
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return 0;
    }
    
     public QuestionDTO getQuestionById(int questionId) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBHelper.getConnection();
            String sql = "SELECT question_content, subjectId, status "
                    + "FROM question "
                    + "WHERE questionId = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, questionId);
            rs = ps.executeQuery();
            if (rs.next()) {
                String questionContent = rs.getString("question_content");
                String subjectId = rs.getString("subjectId");
                boolean status = rs.getBoolean("status");
                return new QuestionDTO(questionId, questionContent, subjectId, "", status);
                
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return null;
    }
     
     
    
    

}
