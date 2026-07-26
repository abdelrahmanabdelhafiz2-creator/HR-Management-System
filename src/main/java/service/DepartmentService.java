package service;

import dao.DepartmentDAO;
import dto.DepartmentDTO;

import java.util.List;

public class DepartmentService {

    private DepartmentDAO departmentDAO;

    public DepartmentService() {
        departmentDAO = new DepartmentDAO();
    }

    // عرض كل الأقسام
    public List<DepartmentDTO> getAllDepartments() {
        return departmentDAO.findAll();
    }

    // البحث عن قسم بالـ ID
    public DepartmentDTO getDepartmentById(int id) {
        if (id <= 0) {
            return null;
        }
        return departmentDAO.findById(id);
    }

    // إضافة قسم جديد
    public boolean addDepartment(DepartmentDTO department) {
        System.out.println("========== DEPT SERVICE ==========");
        System.out.println("Name: " + department.getName());

        if (department.getName() == null || department.getName().trim().isEmpty()) {
            System.out.println("Name is empty or null - returning false");
            return false;
        }

        System.out.println("Calling DAO save...");
        boolean result = departmentDAO.save(department);
        System.out.println("DAO result: " + result);
        return result;
    }

    // تعديل قسم
    public boolean updateDepartment(DepartmentDTO department) {
        if (department.getId() <= 0) {
            return false;
        }
        if (department.getName() == null || department.getName().trim().isEmpty()) {
            return false;
        }
        return departmentDAO.update(department);
    }

    // حذف قسم
    public boolean deleteDepartment(int id) {
        if (id <= 0) {
            return false;
        }
        return departmentDAO.delete(id);
    }
}
