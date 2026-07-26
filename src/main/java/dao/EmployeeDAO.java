package dao;

import dto.EmployeeDTO;
import util.DBConnection;
import util.PasswordUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {

    // بنعمل JOIN مع department عشان نجيب اسم القسم بدل ما نعرض رقم الـ ID بس،
    // وبنعمل JOIN تاني (self join) مع نفس جدول employee عشان نجيب اسم المدير.
    private static final String BASE_SELECT =
            "SELECT e.*, d.name AS department_name, m.name AS manager_name " +
            "FROM employee e " +
            "LEFT JOIN department d ON e.department_id = d.id " +
            "LEFT JOIN employee m ON e.manager_id = m.id ";

    public List<EmployeeDTO> findAll() {
        List<EmployeeDTO> employees = new ArrayList<>();
        String sql = BASE_SELECT + "ORDER BY e.id";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                employees.add(mapResultSetToEmployee(resultSet));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return employees;
    }

    public EmployeeDTO findById(int id) {
        String sql = BASE_SELECT + "WHERE e.id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToEmployee(resultSet);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    // ممكن تستخدمها في دروب داون "المدير" أو أي مكان محتاج قايمة موظفين خفيفة
    public List<EmployeeDTO> findAllForManagerSelection() {
        List<EmployeeDTO> employees = new ArrayList<>();
        String sql = "SELECT id, name FROM employee ORDER BY name";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                EmployeeDTO employee = new EmployeeDTO();
                employee.setId(resultSet.getInt("id"));
                employee.setName(resultSet.getString("name"));
                employees.add(employee);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return employees;
    }

    public boolean save(EmployeeDTO employee) {
        String sql = "INSERT INTO employee (name, email, password, salary, role, department_id, manager_id) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, employee.getName());
            statement.setString(2, employee.getEmail());
            // بنعمل Hash للباسورد قبل ما نخزنه - مبنخزنش plain text في الداتا بيز
            statement.setString(3, PasswordUtil.hash(employee.getPassword()));
            statement.setDouble(4, employee.getSalary());
            statement.setString(5, employee.getRole());
            statement.setInt(6, employee.getDepartmentId());

            if (employee.getManagerId() == null) {
                statement.setNull(7, Types.INTEGER);
            } else {
                statement.setInt(7, employee.getManagerId());
            }

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean update(EmployeeDTO employee) {
        // لو المستخدم سايب خانة الباسورد فاضية في فورم التعديل، منحدثهاش ونسيب القديمة
        boolean changePassword = employee.getPassword() != null && !employee.getPassword().trim().isEmpty();

        String sql = changePassword
                ? "UPDATE employee SET name = ?, email = ?, password = ?, salary = ?, role = ?, department_id = ?, manager_id = ? WHERE id = ?"
                : "UPDATE employee SET name = ?, email = ?, salary = ?, role = ?, department_id = ?, manager_id = ? WHERE id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            int index = 1;
            statement.setString(index++, employee.getName());
            statement.setString(index++, employee.getEmail());

            if (changePassword) {
                statement.setString(index++, PasswordUtil.hash(employee.getPassword()));
            }

            statement.setDouble(index++, employee.getSalary());
            statement.setString(index++, employee.getRole());
            statement.setInt(index++, employee.getDepartmentId());

            if (employee.getManagerId() == null) {
                statement.setNull(index++, Types.INTEGER);
            } else {
                statement.setInt(index++, employee.getManagerId());
            }

            statement.setInt(index, employee.getId());

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean delete(int id) {
        String sql = "DELETE FROM employee WHERE id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // Helper method to map ResultSet to EmployeeDTO
    private EmployeeDTO mapResultSetToEmployee(ResultSet resultSet) throws SQLException {
        EmployeeDTO employee = new EmployeeDTO();
        employee.setId(resultSet.getInt("id"));
        employee.setName(resultSet.getString("name"));
        employee.setEmail(resultSet.getString("email"));
        employee.setPassword(resultSet.getString("password"));
        employee.setSalary(resultSet.getDouble("salary"));
        employee.setRole(resultSet.getString("role"));
        employee.setDepartmentId(resultSet.getInt("department_id"));
        employee.setDepartmentName(resultSet.getString("department_name"));
        employee.setManagerName(resultSet.getString("manager_name"));

        int managerId = resultSet.getInt("manager_id");
        if (resultSet.wasNull()) {
            employee.setManagerId(null);
        } else {
            employee.setManagerId(managerId);
        }

        return employee;
    }
}
