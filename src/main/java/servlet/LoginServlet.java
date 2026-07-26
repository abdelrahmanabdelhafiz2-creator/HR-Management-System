package servlet;

import dto.UserDTO;
import service.UserService;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private UserService userService;

    @Override
    public void init() {
        userService = new UserService();
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // بندخل بس لو الإيميل موجود في جدول users (مديري الـ HR)
        // الموظفين العاديين في جدول employee مش بيدخلوا على النظام ده خالص
        UserDTO user = userService.login(email, password);

        if (user != null) {
            // بنعمل invalidate لأي session قديمة (Session Fixation Protection)
            // قبل ما نعمل session جديدة للمستخدم بعد تسجيل الدخول بنجاح
            HttpSession oldSession = request.getSession(false);
            if (oldSession != null) {
                oldSession.invalidate();
            }

            HttpSession session = request.getSession(true);
            session.setAttribute("loggedUser", user);
            response.sendRedirect(request.getContextPath() + "/dashboard");
        } else {
            response.sendRedirect(request.getContextPath() + "/views/login.jsp?error=true");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws IOException {
        response.sendRedirect(request.getContextPath() + "/views/login.jsp");
    }
}
