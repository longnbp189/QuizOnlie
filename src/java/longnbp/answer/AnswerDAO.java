/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longnbp.answer;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import longnbp.utilities.DBHelper;

/**
 *
 * @author Admin
 */
public class AnswerDAO implements Serializable{
    public List<AnswerDTO> getAnswerList(int questionId) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<AnswerDTO> list = null;
        try {
            con = DBHelper.getConnection();
            String sql = "SELECT answerId, questionId, answer_content, status "
                    + "FROM answer "
                    + "WHERE questionId = ?";
//                    + "ORDER BY NEWID() ";
            ps = con.prepareStatement(sql);
            ps.setInt(1, questionId);
            rs = ps.executeQuery();
            while (rs.next()) {
                if (list == null) {
                    list = new ArrayList<>();
                }
                int id = rs.getInt("answerId");
                String answer_content = rs.getString("answer_content");
                boolean status = rs.getBoolean("status");
                list.add(new AnswerDTO(id, questionId, answer_content, status));
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
    
    public void updateAnswer(int answerId, String answerContent, boolean status) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBHelper.getConnection();
            String sql = "UPDATE answer "
                    + "SET answer_content = ?, status = ? "
                    + "WHERE answerId = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, answerContent);
            ps.setBoolean(2, status);
            ps.setInt(3, answerId);
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
    
    public void insertAnswer(int questionId, String answerContent, boolean status) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBHelper.getConnection();
            String sql = "INSERT INTO answer (answer_content, questionId, status) "
                    + "VALUES (?,?,?) ";
            ps = con.prepareStatement(sql);
            ps.setString(1, answerContent);
            ps.setInt(2, questionId);
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
    
    public List<AnswerDTO> getAnswerListFalse(int questionId) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<AnswerDTO> list = null;
        try {
            con = DBHelper.getConnection();
            String sql = "SELECT answerId, questionId, answer_content "
                    + "FROM answer "
                    + "WHERE questionId = ? AND status = 0";
            ps = con.prepareStatement(sql);
            ps.setInt(1, questionId);
            rs = ps.executeQuery();
            while (rs.next()) {
                if (list == null) {
                    list = new ArrayList<>();
                }
                int id = rs.getInt("answerId");
                String answer_content = rs.getString("answer_content");
                list.add(new AnswerDTO(id, questionId, answer_content, false));
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
        
    public AnswerDTO getAnswerTrue(int questionId) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBHelper.getConnection();
            String sql = "SELECT answerId, questionId, answer_content "
                    + "FROM answer "
                    + "WHERE questionId = ? AND status = 1";
            ps = con.prepareStatement(sql);
            ps.setInt(1, questionId);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("answerId");
                String answer_content = rs.getString("answer_content");
                return new AnswerDTO(id, questionId, answer_content, true);
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
    
    public boolean isTrueAnswer(int answerId) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBHelper.getConnection();
            String sql = "SELECT status "
                    + "FROM answer "
                    + "WHERE answerId = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, answerId);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getBoolean("status");
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
        return false;
    }
}
