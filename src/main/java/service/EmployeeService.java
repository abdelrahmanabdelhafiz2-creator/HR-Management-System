package service;

import dao.EmployeeDAO;
import dto.EmployeeDTO;

import java.util.List;

public class EmployeeService {

    private EmployeeDAO employeeDAO;

    public EmployeeService() {
        employeeDAO = new EmployeeDAO();
    }

    public List<EmployeeDTO> getAllEmployees() {
        return employeeDAO.findAll();
    }

    // قايمة مبسطة (id + name) بتتستخدم في دروب داون اختيار "المدير" في الفورمات
    public List<EmployeeDTO> getEmployeesForManagerSelection() {
        return employeeDAO.findAllForManagerSelection();
    }

    public EmployeeDTO getEmployeeById(int id) {
        if (id <= 0) {
            return null;
        }
        return employeeDAO.findById(id);
    }

    public boolean addEmployee(EmployeeDTO employee) {
        if (employee.getName() == null || employee.getName().trim().isEmpty()) {
            return false;
        }
        if (employee.getEmail() == null || employee.getEmail().trim().isEmpty()) {
            return false;
        }
        if (employee.getPassword() == null || employee.getPassword().trim().isEmpty()) {
            return false;
        }
        if (employee.getSalary() <= 0) {
            return false;
        }
        if (employee.getRole() == null || employee.getRole().trim().isEmpty()) {
            return false;
        }
        if (employee.getDepartmentId() <= 0) {
            return false;
        }
        // managerId يمكن أن يكون null أو >0
        return employeeDAO.save(employee);
    }

    public boolean updateEmployee(EmployeeDTO employee) {
        if (employee.getId() <= 0) {
            return false;
        }
        if (employee.getSalary() <= 0) {
            return false;
        }
        return employeeDAO.update(employee);
    }

    public boolean deleteEmployee(int id) {
        if (id <= 0) {
            return false;
        }
        return employeeDAO.delete(id);
    }

    // ملحوظة: مفيش login() هنا عن قصد.
    // الموظفين العاديين مش بيدخلوا على النظام ده خالص، الدخول بقى بس
    // لمديري الـ HR الموجودين في جدول users (شوف UserService/UserDAO).
}
