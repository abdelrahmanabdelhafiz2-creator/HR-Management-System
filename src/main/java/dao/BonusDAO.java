package dao;

import dto.BonusDTO;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BonusDAO {

    // بنعمل JOIN مع employee و department عشان نعرض اسم الموظف وقسمه
    // بدل ما نعرض employee_id رقم خام في الجدول
    private static final String BASE_SELECT =
            "SELECT b.*, e.name AS employee_name, d.name AS department_name " +
            "FROM bonus b " +
            "LEFT JOIN employee e ON b.employee_id = e.id " +
            "LEFT JOIN department d ON e.department_id = d.id ";

    public List<BonusDTO> findAll() {
        List<BonusDTO> bonuses = new ArrayList<>();
        String sql = BASE_SELECT + "ORDER BY b.bonus_date DESC";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                bonuses.add(mapResultSetToBonus(resultSet));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bonuses;
    }

    public BonusDTO findById(int id) {
        String sql = BASE_SELECT + "WHERE b.id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToBonus(resultSet);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean save(BonusDTO bonus) {
        String sql = "INSERT INTO bonus (employee_id, amount, reason, bonus_date) VALUES (?, ?, ?, ?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, bonus.getEmployeeId());
            statement.setDouble(2, bonus.getAmount());
            statement.setString(3, bonus.getReason());
            statement.setDate(4, bonus.getBonusDate());

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean update(BonusDTO bonus) {
        String sql = "UPDATE bonus SET employee_id = ?, amount = ?, reason = ?, bonus_date = ? WHERE id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, bonus.getEmployeeId());
            statement.setDouble(2, bonus.getAmount());
            statement.setString(3, bonus.getReason());
            statement.setDate(4, bonus.getBonusDate());
            statement.setInt(5, bonus.getId());

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean delete(int id) {
        String sql = "DELETE FROM bonus WHERE id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    private BonusDTO mapResultSetToBonus(ResultSet resultSet) throws SQLException {
        BonusDTO bonus = new BonusDTO();
        bonus.setId(resultSet.getInt("id"));
        bonus.setEmployeeId(resultSet.getInt("employee_id"));
        bonus.setAmount(resultSet.getDouble("amount"));
        bonus.setReason(resultSet.getString("reason"));
        bonus.setBonusDate(resultSet.getDate("bonus_date"));
        bonus.setEmployeeName(resultSet.getString("employee_name"));
        bonus.setDepartmentName(resultSet.getString("department_name"));
        return bonus;
    }
}
