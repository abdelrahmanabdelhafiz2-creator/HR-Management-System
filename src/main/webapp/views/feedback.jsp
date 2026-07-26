<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="dto.FeedbackDTO" %>
<%@ page import="util.Escape" %>
<%@ include file="../layout/header.jsp" %>

<main class="feedback-page">

    <section class="page-header">

        <div class="page-title">
            <h1>Employee Feedback</h1>
            <p>Performance reviews and employee evaluations.</p>
        </div>

    </section>

    <div class="table-wrapper">
        <table>
            <thead>
            <tr>
                <th>Employee</th>
                <th>Department</th>
                <th>Rating</th>
                <th>Comment</th>
                <th>Date</th>
                <th>Action</th>
            </tr>
            </thead>
            <tbody>
            <%
                List<FeedbackDTO> feedbacks = (List<FeedbackDTO>) request.getAttribute("feedbacks");
                if (feedbacks != null) {
                    for (FeedbackDTO feedback : feedbacks) {
            %>
            <tr>
                <td><%= feedback.getEmployeeName() != null ? Escape.html(feedback.getEmployeeName()) : "#" + feedback.getEmployeeId() %></td>
                <td><%= feedback.getDepartmentName() != null ? Escape.html(feedback.getDepartmentName()) : "-" %></td>
                <td><%= feedback.getRate() %> / 5</td>
                <td><%= Escape.html(feedback.getComment()) %></td>
                <td><%= feedback.getFeedbackDate() %></td>
                <td>
                    <form action="${pageContext.request.contextPath}/feedback" method="post">
                        <input type="hidden" name="action" value="delete">
                        <input type="hidden" name="id" value="<%= feedback.getId() %>">
                        <button type="submit" class="btn-delete">Delete</button>
                    </form>
                </td>
            </tr>
            <%
                    }
                }
            %>
            </tbody>
        </table>
    </div>

</main>

<%@ include file="../layout/footer.jsp" %>
