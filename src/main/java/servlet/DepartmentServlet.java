package servlet;

import dto.DepartmentDTO;
import service.DepartmentService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/department")
public class DepartmentServlet extends HttpServlet {

    private DepartmentService departmentService;

    @Override
    public void init() {
        departmentService = new DepartmentService();
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        try {
            if (action == null) {
                List<DepartmentDTO> departments = departmentService.getAllDepartments();
                request.setAttribute("departments", departments);
                request.getRequestDispatcher("/views/departments.jsp").forward(request, response);

            } else if (action.equals("delete")) {
                int id = Integer.parseInt(request.getParameter("id"));
                departmentService.deleteDepartment(id);
                response.sendRedirect("department");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws IOException {

        String action = request.getParameter("action");

        try {
            if (action == null) {
                response.sendRedirect("department");
                return;
            }

            if (action.equals("save")) {
                String name = request.getParameter("name");

                DepartmentDTO department = new DepartmentDTO();
                department.setName(name);

                boolean result = departmentService.addDepartment(department);

                if (result) {
                    response.sendRedirect("department");
                } else {
                    response.sendRedirect("error.jsp");
                }

            } else if (action.equals("delete")) {
                int id = Integer.parseInt(request.getParameter("id"));
                departmentService.deleteDepartment(id);
                response.sendRedirect("department");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
}
