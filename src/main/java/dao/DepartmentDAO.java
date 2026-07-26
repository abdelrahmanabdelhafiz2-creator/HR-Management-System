package dao;

import dto.DepartmentDTO;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDAO {

    // بنحسب عدد الموظفين في كل قسم عن طريق LEFT JOIN + COUNT
    // عشان نعرضه في صفحة الأقسام من غير ما نعمل استعلام إضافي لكل كارت
    private static final String BASE_SELECT =
            "SELECT d.id, d.name, COUNT(e.id) AS employee_count " +
            "FROM department d " +
            "LEFT JOIN employee e ON e.department_id = d.id ";

    // عرض كل الأقسام
    public List<DepartmentDTO> findAll() {
        List<DepartmentDTO> departments = new ArrayList<>();
        String sql = BASE_SELECT + "GROUP BY d.id, d.name ORDER BY d.id";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet result = statement.executeQuery()) {

            while (result.next()) {
                departments.add(mapResultSetToDepartment(result));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return departments;
    }

    // البحث عن قسم بالـ ID
    public DepartmentDTO findById(int id) {
        String sql = BASE_SELECT + "WHERE d.id = ? GROUP BY d.id, d.name";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    return mapResultSetToDepartment(result);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    // إضافة قسم جديد
    public boolean save(DepartmentDTO department) {
        String sql = "INSERT INTO department (name) VALUES (?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, department.getName());

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // تعديل قسم
    public boolean update(DepartmentDTO department) {
        String sql = "UPDATE department SET name = ? WHERE id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, department.getName());
            statement.setInt(2, department.getId());

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // حذف قسم
    public boolean delete(int id) {
        String sql = "DELETE FROM department WHERE id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    private DepartmentDTO mapResultSetToDepartment(ResultSet result) throws SQLException {
        DepartmentDTO department = new DepartmentDTO();
        department.setId(result.getInt("id"));
        department.setName(result.getString("name"));
        department.setEmployeeCount(result.getInt("employee_count"));
        // description و managerId مش موجودين كأعمدة في الجدول الحالي
        department.setDescription(null);
        department.setManagerId(null);
        return department;
    }
}
