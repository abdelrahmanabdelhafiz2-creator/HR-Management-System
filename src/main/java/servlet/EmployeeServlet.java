package servlet;

import dto.DepartmentDTO;
import dto.EmployeeDTO;
import service.DepartmentService;
import service.EmployeeService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/employee")
public class EmployeeServlet extends HttpServlet {

    private EmployeeService employeeService;
    private DepartmentService departmentService;

    @Override
    public void init() {
        employeeService = new EmployeeService();
        departmentService = new DepartmentService();
    }

    // ==========================
    // GET: عرض القائمة - تفاصيل - تعديل
    // ==========================
    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        try {
            if (action == null) {
                // عرض كل الموظفين
                List<EmployeeDTO> employees = employeeService.getAllEmployees();
                request.setAttribute("employees", employees);
                request.getRequestDispatcher("/views/employees.jsp").forward(request, response);

            } else if (action.equals("new")) {
                // فتح صفحة إضافة موظف - محتاجة قايمة الأقسام والمديرين عشان الدروب داون
                List<DepartmentDTO> departments = departmentService.getAllDepartments();
                List<EmployeeDTO> managers = employeeService.getEmployeesForManagerSelection();
                request.setAttribute("departments", departments);
                request.setAttribute("managers", managers);
                request.getRequestDispatcher("/views/add-employee.jsp").forward(request, response);

            } else if (action.equals("view")) {
                // عرض تفاصيل موظف
                int id = Integer.parseInt(request.getParameter("id"));
                EmployeeDTO employee = employeeService.getEmployeeById(id);
                request.setAttribute("employee", employee);
                request.getRequestDispatcher("/views/employee-details.jsp").forward(request, response);

            } else if (action.equals("edit")) {
                // فتح صفحة التعديل - برضو محتاجة قايمة الأقسام والمديرين
                int id = Integer.parseInt(request.getParameter("id"));
                EmployeeDTO employee = employeeService.getEmployeeById(id);
                List<DepartmentDTO> departments = departmentService.getAllDepartments();
                List<EmployeeDTO> managers = employeeService.getEmployeesForManagerSelection();
                request.setAttribute("employee", employee);
                request.setAttribute("departments", departments);
                request.setAttribute("managers", managers);
                request.getRequestDispatcher("/views/edit-employee.jsp").forward(request, response);

            } else {
                response.sendRedirect(request.getContextPath() + "/employee");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }

    // ==========================
    // POST: إضافة - تعديل - حذف
    // ==========================
    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws IOException {

        String action = request.getParameter("action");

        try {
            if (action == null) {
                response.sendRedirect("employee");
                return;
            }

            if (action.equals("save")) {
                // إضافة
                EmployeeDTO employee = new EmployeeDTO();
                employee.setName(request.getParameter("name"));
                employee.setEmail(request.getParameter("email"));
                employee.setPassword(request.getParameter("password"));
                employee.setSalary(Double.parseDouble(request.getParameter("salary")));
                employee.setRole(request.getParameter("role"));
                employee.setDepartmentId(Integer.parseInt(request.getParameter("departmentId")));

                String managerIdParam = request.getParameter("managerId");
                if (managerIdParam != null && !managerIdParam.isEmpty() && !managerIdParam.equals("0")) {
                    employee.setManagerId(Integer.parseInt(managerIdParam));
                } else {
                    employee.setManagerId(null);
                }

                boolean result = employeeService.addEmployee(employee);
                if (result) {
                    response.sendRedirect("employee");
                } else {
                    response.sendRedirect("error.jsp");
                }

            } else if (action.equals("update")) {
                // تعديل
                EmployeeDTO employee = new EmployeeDTO();
                employee.setId(Integer.parseInt(request.getParameter("id")));
                employee.setName(request.getParameter("name"));
                employee.setEmail(request.getParameter("email"));
                employee.setPassword(request.getParameter("password"));
                employee.setSalary(Double.parseDouble(request.getParameter("salary")));
                employee.setRole(request.getParameter("role"));
                employee.setDepartmentId(Integer.parseInt(request.getParameter("departmentId")));

                String managerIdParam = request.getParameter("managerId");
                if (managerIdParam != null && !managerIdParam.isEmpty() && !managerIdParam.equals("0")) {
                    employee.setManagerId(Integer.parseInt(managerIdParam));
                } else {
                    employee.setManagerId(null);
                }

                boolean result = employeeService.updateEmployee(employee);
                if (result) {
                    response.sendRedirect("employee");
                } else {
                    response.sendRedirect("error.jsp");
                }

            } else if (action.equals("delete")) {
                int id = Integer.parseInt(request.getParameter("id"));
                employeeService.deleteEmployee(id);
                response.sendRedirect("employee");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
}
