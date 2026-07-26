package servlet;

import dto.EmployeeDTO;
import service.BonusService;
import service.DepartmentService;
import service.EmployeeService;
import service.PromotionService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {

    private EmployeeService employeeService;
    private DepartmentService departmentService;
    private PromotionService promotionService;
    private BonusService bonusService;

    @Override
    public void init() {
        employeeService = new EmployeeService();
        departmentService = new DepartmentService();
        promotionService = new PromotionService();
        bonusService = new BonusService();
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        // جلب الإحصائيات
        List<EmployeeDTO> employees = employeeService.getAllEmployees();
        int employeeCount = employees.size();
        int departmentCount = departmentService.getAllDepartments().size();
        int promotionCount = promotionService.getAllPromotions().size();
        int bonusCount = bonusService.getAllBonuses().size();

        // تعيين القيم في request
        request.setAttribute("employees", employees);
        request.setAttribute("employeeCount", employeeCount);
        request.setAttribute("departmentCount", departmentCount);
        request.setAttribute("promotionCount", promotionCount);
        request.setAttribute("bonusCount", bonusCount);

        // التوجيه إلى dashboard.jsp
        request.getRequestDispatcher("/views/dashboard.jsp").forward(request, response);
    }
}
