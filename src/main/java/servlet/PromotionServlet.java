package servlet;

import dto.PromotionDTO;
import service.PromotionService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

@WebServlet("/promotion")
public class PromotionServlet extends HttpServlet {

    private PromotionService promotionService;

    @Override
    public void init() {
        promotionService = new PromotionService();
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        try {
            if (action == null) {
                List<PromotionDTO> promotions = promotionService.getAllPromotions();
                request.setAttribute("promotions", promotions);
                request.getRequestDispatcher("/views/promotions.jsp").forward(request, response);

            } else if (action.equals("delete")) {
                int id = Integer.parseInt(request.getParameter("id"));
                promotionService.deletePromotion(id);
                response.sendRedirect("promotion");
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
                response.sendRedirect("promotion");
                return;
            }

            if (action.equals("save")) {
                PromotionDTO promotion = new PromotionDTO();
                promotion.setEmployeeId(Integer.parseInt(request.getParameter("employeeId")));
                promotion.setOldRole(request.getParameter("oldRole"));
                promotion.setNewRole(request.getParameter("newRole"));
                promotion.setOldSalary(Double.parseDouble(request.getParameter("oldSalary")));
                promotion.setNewSalary(Double.parseDouble(request.getParameter("newSalary")));
                promotion.setPromotionDate(Date.valueOf(request.getParameter("promotionDate")));

                boolean result = promotionService.addPromotion(promotion);
                if (result) {
                    response.sendRedirect("promotion");
                } else {
                    response.sendRedirect("error.jsp");
                }

            } else if (action.equals("delete")) {
                int id = Integer.parseInt(request.getParameter("id"));
                promotionService.deletePromotion(id);
                response.sendRedirect("promotion");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
}
