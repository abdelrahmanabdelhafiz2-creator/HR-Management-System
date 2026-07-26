package servlet;

import dto.FeedbackDTO;
import service.FeedbackService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

@WebServlet("/feedback")
public class FeedbackServlet extends HttpServlet {

    private FeedbackService feedbackService;

    @Override
    public void init() {
        feedbackService = new FeedbackService();
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        try {
            if (action == null) {
                List<FeedbackDTO> feedbacks = feedbackService.getAllFeedback();
                request.setAttribute("feedbacks", feedbacks);
                request.getRequestDispatcher("/views/feedback.jsp").forward(request, response);

            } else if (action.equals("delete")) {
                int id = Integer.parseInt(request.getParameter("id"));
                feedbackService.deleteFeedback(id);
                response.sendRedirect("feedback");
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
                response.sendRedirect("feedback");
                return;
            }

            if (action.equals("save")) {
                FeedbackDTO feedback = new FeedbackDTO();
                feedback.setEmployeeId(Integer.parseInt(request.getParameter("employeeId")));
                feedback.setRate(Integer.parseInt(request.getParameter("rate")));
                feedback.setComment(request.getParameter("comment"));
                feedback.setFeedbackDate(Date.valueOf(request.getParameter("date")));

                boolean result = feedbackService.addFeedback(feedback);
                if (result) {
                    response.sendRedirect("feedback");
                } else {
                    response.sendRedirect("error.jsp");
                }

            } else if (action.equals("delete")) {
                int id = Integer.parseInt(request.getParameter("id"));
                feedbackService.deleteFeedback(id);
                response.sendRedirect("feedback");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
}
