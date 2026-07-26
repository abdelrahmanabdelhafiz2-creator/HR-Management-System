package filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebFilter("/*")
public class AuthenticationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String uri = httpRequest.getRequestURI();

        // صفحات مسموح بها بدون Login
        if (uri.contains("/login")
                || uri.contains("/login.jsp")
                || uri.contains("/css/")
                || uri.contains("/js/")
                || uri.contains("/images/")
                || uri.contains("/index.jsp")
                || uri.equals(httpRequest.getContextPath() + "/")
                || uri.equals(httpRequest.getContextPath())) {

            chain.doFilter(request, response);
            return;
        }

        HttpSession session = httpRequest.getSession(false);

        if (session != null && session.getAttribute("loggedUser") != null) {
            chain.doFilter(request, response);
        } else {
            httpResponse.sendRedirect(
                    httpRequest.getContextPath() + "/views/login.jsp"
            );
        }
    }
}
