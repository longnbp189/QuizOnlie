/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longbp.historyquiz;

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
public class HistoryQuizDAO implements Serializable {

    public void addResultQuizDetail(HistoryQuizDTO dto) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBHelper.getConnection();
            String sql = "INSERT INTO historyQuiz (subjectId, time, correct_answer, grade, email) "
                    + "VALUES (?, ?, ?, ?, ?) ";
            ps = con.prepareStatement(sql);
            ps.setInt(3, dto.getCorrect_answer());
            ps.setFloat(4, dto.getGrade());
            ps.setString(5, dto.getEmail());
            ps.setString(1, dto.getSubjectId());
            Timestamp date = new Timestamp((new Date()).getTime());
            ps.setTimestamp(2, date);
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

    public List<HistoryQuizDTO> getQuizHistoryBySubject(String username, String subjectId) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<HistoryQuizDTO> list = null;
        try {
            con = DBHelper.getConnection();
            String sql = "SELECT h.id, s.subjectName, h.time, h.correct_answer, h.grade "
                    + "FROM historyQuiz h JOIN subject s ON h.subjectId = s.subjectId "
                    + "WHERE h.email = ? AND h.subjectId = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, subjectId);
            rs = ps.executeQuery();
            while (rs.next()) {
                if (list == null) {
                    list = new ArrayList<>();
                }
                int historyId = rs.getInt("id");
                String subject = rs.getString("subjectName");
                int correct_answer = rs.getInt("correct_answer");
                float grade = rs.getFloat("grade");
                Timestamp dateDB = rs.getTimestamp("time");
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                String createDate = format.format(dateDB);
                list.add(new HistoryQuizDTO(subject, username, createDate, historyId ,correct_answer, grade));
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

    public List<HistoryQuizDTO> getAllQuizHistory(String username) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<HistoryQuizDTO> list = null;
        try {
            con = DBHelper.getConnection();
            String sql = "SELECT  h.id, s.subjectName, h.time, h.correct_answer, h.grade "
                    + "FROM historyQuiz h JOIN subject s ON h.subjectId = s.subjectId "
                    + "WHERE h.email = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, username);
            rs = ps.executeQuery();
            while (rs.next()) {
                if (list == null) {
                    list = new ArrayList<>();
                }
                int historyId = rs.getInt("id");
                String subject = rs.getString("subjectName");
                int correct_answer = rs.getInt("correct_answer");
                float grade = rs.getFloat("grade");
                Timestamp dateDB = rs.getTimestamp("time");
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                String createDate = format.format(dateDB);
                list.add(new HistoryQuizDTO(subject, username, createDate, historyId, correct_answer, grade));
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
    
     public int getHistoryIdMax(String email) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBHelper.getConnection();
            String sql = "SELECT TOP 1 id "
                    + "FROM historyQuiz "
                    + "WHERE email = ? "
                    + "ORDER BY id DESC ";

            ps = con.prepareStatement(sql);
            ps.setString(1, email);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
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
}
