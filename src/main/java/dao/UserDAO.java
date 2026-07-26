package dao;

import dto.UserDTO;
import util.DBConnection;
import util.PasswordUtil;

import java.sql.*;

/**
 * الجدول users مخصص لمديري الـ HR بس، وهو Read-Only من الموقع ده عن قصد:
 * مفيش هنا save() ولا update() ولا delete() - البيانات بتتضاف/تتعدل من مكان تاني
 * (مش من الموقع ده)، والموقع بيقرا منه بس وقت تسجيل الدخول.
 */
public class UserDAO {

    public UserDTO findByEmailAndPassword(String email, String password) {
        String sql = "SELECT * FROM users WHERE email = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, email);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String storedPassword = resultSet.getString("password");

                    boolean valid = PasswordUtil.isHashed(storedPassword)
                            ? PasswordUtil.verify(password, storedPassword)
                            : password != null && password.equals(storedPassword);

                    if (valid) {
                        UserDTO user = new UserDTO();
                        user.setId(resultSet.getInt("id"));
                        user.setName(resultSet.getString("name"));
                        user.setEmail(resultSet.getString("email"));
                        return user;
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
