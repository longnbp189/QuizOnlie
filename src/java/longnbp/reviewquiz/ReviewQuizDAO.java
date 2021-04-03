/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longnbp.reviewquiz;

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
public class ReviewQuizDAO implements Serializable {

    public List<ReviewQuizView> getReviewByHistoryId(int id) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<ReviewQuizView> list = null;
        try {
            con = DBHelper.getConnection();
            String sql = "select rq.questionId, q.question_content, a.answer_content, a.status "
                    + "from reviewQuiz rq join historyQuiz hq on rq.historyQuizId = hq.id "
                    + "join question q on rq.questionId = q.questionId "
                    + "left join answer a on rq.answerId = a.answerId "
                    + "where hq.id = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                if (list == null) {
                    list = new ArrayList<>();
                }
                int questionId = rs.getInt("questionId");
                String questionContent = rs.getString("question_content");
                String answerContent = rs.getString("answer_content");
                boolean status = rs.getBoolean("status");
                list.add(new ReviewQuizView(questionContent, answerContent, answerContent, questionId, status));
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
    
    public void insertReviewQuiz(ReviewQuizDTO dto) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBHelper.getConnection();
            String sql = "INSERT INTO reviewQuiz (historyQuizId, questionId, answerId) "
                    + "VALUES (?,?,?) ";
            ps = con.prepareStatement(sql);
            ps.setInt(1, dto.getHistoryQuizId());
            ps.setInt(2, dto.getQuestionId());
            ps.setInt(3, dto.getAnswerId());
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
    
    
    public void insertReviewQuizWithoutAnswer(ReviewQuizDTO dto) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBHelper.getConnection();
            String sql = "INSERT INTO reviewQuiz (historyQuizId, questionId) "
                    + "VALUES (?,?) ";
            ps = con.prepareStatement(sql);
            ps.setInt(1, dto.getHistoryQuizId());
            ps.setInt(2, dto.getQuestionId());
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
    
    public boolean isTest(int questionId) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBHelper.getConnection();
            String sql = "SELECT questionId "
                    + "FROM reviewQuiz "
                    + "WHERE questionId = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, questionId);
            rs = ps.executeQuery();
            if (rs.next()) {
                return true;
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
