package dao;

import dto.PromotionDTO;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PromotionDAO {

    private static final String BASE_SELECT =
            "SELECT p.*, e.name AS employee_name, d.name AS department_name " +
            "FROM promotion p " +
            "LEFT JOIN employee e ON p.employee_id = e.id " +
            "LEFT JOIN department d ON e.department_id = d.id ";

    public List<PromotionDTO> findAll() {
        List<PromotionDTO> promotions = new ArrayList<>();
        String sql = BASE_SELECT + "ORDER BY p.promotion_date DESC";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                promotions.add(mapResultSetToPromotion(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return promotions;
    }

    public PromotionDTO findById(int id) {
        String sql = BASE_SELECT + "WHERE p.id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToPromotion(rs);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean save(PromotionDTO promotion) {
        String sql = "INSERT INTO promotion (employee_id, old_role, new_role, old_salary, new_salary, promotion_date) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, promotion.getEmployeeId());
            ps.setString(2, promotion.getOldRole());
            ps.setString(3, promotion.getNewRole());
            ps.setDouble(4, promotion.getOldSalary());
            ps.setDouble(5, promotion.getNewSalary());
            ps.setDate(6, promotion.getPromotionDate());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean update(PromotionDTO promotion) {
        String sql = "UPDATE promotion SET new_role = ?, new_salary = ?, promotion_date = ? WHERE id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, promotion.getNewRole());
            ps.setDouble(2, promotion.getNewSalary());
            ps.setDate(3, promotion.getPromotionDate());
            ps.setInt(4, promotion.getId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean delete(int id) {
        String sql = "DELETE FROM promotion WHERE id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    private PromotionDTO mapResultSetToPromotion(ResultSet rs) throws SQLException {
        PromotionDTO promotion = new PromotionDTO();
        promotion.setId(rs.getInt("id"));
        promotion.setEmployeeId(rs.getInt("employee_id"));
        promotion.setOldRole(rs.getString("old_role"));
        promotion.setNewRole(rs.getString("new_role"));
        promotion.setOldSalary(rs.getDouble("old_salary"));
        promotion.setNewSalary(rs.getDouble("new_salary"));
        promotion.setPromotionDate(rs.getDate("promotion_date"));
        promotion.setEmployeeName(rs.getString("employee_name"));
        promotion.setDepartmentName(rs.getString("department_name"));
        return promotion;
    }
}
