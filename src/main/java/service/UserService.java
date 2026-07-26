package service;

import dao.UserDAO;
import dto.UserDTO;

public class UserService {

    private final UserDAO userDAO;

    public UserService() {
        userDAO = new UserDAO();
    }

    public UserDTO login(String email, String password) {
        if (email == null || password == null
                || email.trim().isEmpty() || password.trim().isEmpty()) {
            return null;
        }
        return userDAO.findByEmailAndPassword(email.trim(), password);
    }
}
