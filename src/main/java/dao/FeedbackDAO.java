package dao;

import dto.FeedbackDTO;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FeedbackDAO {

    private static final String BASE_SELECT =
            "SELECT f.*, e.name AS employee_name, d.name AS department_name " +
            "FROM feedback f " +
            "LEFT JOIN employee e ON f.employee_id = e.id " +
            "LEFT JOIN department d ON e.department_id = d.id ";

    public List<FeedbackDTO> findAll() {
        List<FeedbackDTO> list = new ArrayList<>();
        String sql = BASE_SELECT + "ORDER BY f.feedback_date DESC";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(mapResultSetToFeedback(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public FeedbackDTO findById(int id) {
        String sql = BASE_SELECT + "WHERE f.id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToFeedback(rs);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean save(FeedbackDTO feedback) {
        String sql = "INSERT INTO feedback (employee_id, rate, comment, feedback_date) VALUES (?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, feedback.getEmployeeId());
            ps.setInt(2, feedback.getRate());
            ps.setString(3, feedback.getComment());
            ps.setDate(4, feedback.getFeedbackDate());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean update(FeedbackDTO feedback) {
        String sql = "UPDATE feedback SET rate = ?, comment = ?, feedback_date = ? WHERE id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, feedback.getRate());
            ps.setString(2, feedback.getComment());
            ps.setDate(3, feedback.getFeedbackDate());
            ps.setInt(4, feedback.getId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean delete(int id) {
        String sql = "DELETE FROM feedback WHERE id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    private FeedbackDTO mapResultSetToFeedback(ResultSet rs) throws SQLException {
        FeedbackDTO feedback = new FeedbackDTO();
        feedback.setId(rs.getInt("id"));
        feedback.setEmployeeId(rs.getInt("employee_id"));
        feedback.setRate(rs.getInt("rate"));
        feedback.setComment(rs.getString("comment"));
        feedback.setFeedbackDate(rs.getDate("feedback_date"));
        feedback.setEmployeeName(rs.getString("employee_name"));
        feedback.setDepartmentName(rs.getString("department_name"));
        return feedback;
    }
}
