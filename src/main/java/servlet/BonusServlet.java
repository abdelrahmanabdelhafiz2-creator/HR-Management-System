package servlet;

import dto.BonusDTO;
import service.BonusService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

@WebServlet("/bonus")
public class BonusServlet extends HttpServlet {

    private BonusService bonusService;

    @Override
    public void init() {
        bonusService = new BonusService();
    }

    // ==========================
    // GET: عرض القائمة - حذف
    // ==========================
    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        try {
            if (action == null) {
                // عرض كل المكافآت
                List<BonusDTO> bonuses = bonusService.getAllBonuses();
                request.setAttribute("bonuses", bonuses);
                request.getRequestDispatcher("/views/bonuses.jsp").forward(request, response);

            } else if (action.equals("delete")) {
                int id = Integer.parseInt(request.getParameter("id"));
                bonusService.deleteBonus(id);
                response.sendRedirect("bonus");
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
                response.sendRedirect("bonus");
                return;
            }

            if (action.equals("save")) {
                // إضافة
                BonusDTO bonus = new BonusDTO();
                bonus.setEmployeeId(Integer.parseInt(request.getParameter("employeeId")));
                bonus.setAmount(Double.parseDouble(request.getParameter("amount")));
                bonus.setReason(request.getParameter("reason"));
                bonus.setBonusDate(Date.valueOf(request.getParameter("bonusDate")));

                boolean result = bonusService.addBonus(bonus);
                if (result) {
                    response.sendRedirect("bonus");
                } else {
                    response.sendRedirect("error.jsp");
                }

            } else if (action.equals("update")) {
                // تعديل
                BonusDTO bonus = new BonusDTO();
                bonus.setId(Integer.parseInt(request.getParameter("id")));
                bonus.setEmployeeId(Integer.parseInt(request.getParameter("employeeId")));
                bonus.setAmount(Double.parseDouble(request.getParameter("amount")));
                bonus.setReason(request.getParameter("reason"));
                bonus.setBonusDate(Date.valueOf(request.getParameter("bonusDate")));

                boolean result = bonusService.updateBonus(bonus);
                if (result) {
                    response.sendRedirect("bonus");
                } else {
                    response.sendRedirect("error.jsp");
                }

            } else if (action.equals("delete")) {
                int id = Integer.parseInt(request.getParameter("id"));
                bonusService.deleteBonus(id);
                response.sendRedirect("bonus");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
}
