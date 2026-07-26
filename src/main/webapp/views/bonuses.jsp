<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="dto.BonusDTO" %>
<%@ page import="util.Escape" %>
<%@ include file="../layout/header.jsp" %>

<main class="bonuses-page">

    <section class="page-header">

        <div class="page-title">
            <h1>Bonuses</h1>
            <p>Employee bonus records.</p>
        </div>

        <a href="${pageContext.request.contextPath}/views/add-bonus.jsp" class="btn-add">
            Add Bonus
        </a>

    </section>

    <div class="table-wrapper">
        <table>
            <thead>
            <tr>
                <th>Employee</th>
                <th>Department</th>
                <th>Amount</th>
                <th>Reason</th>
                <th>Date</th>
                <th>Action</th>
            </tr>
            </thead>
            <tbody>
            <%
                List<BonusDTO> bonuses = (List<BonusDTO>) request.getAttribute("bonuses");
                if (bonuses != null) {
                    for (BonusDTO bonus : bonuses) {
            %>
            <tr>
                <td><%= bonus.getEmployeeName() != null ? Escape.html(bonus.getEmployeeName()) : "#" + bonus.getEmployeeId() %></td>
                <td><%= bonus.getDepartmentName() != null ? Escape.html(bonus.getDepartmentName()) : "-" %></td>
                <td><%= bonus.getAmount() %></td>
                <td><%= Escape.html(bonus.getReason()) %></td>
                <td><%= bonus.getBonusDate() %></td>
                <td>
                    <form action="${pageContext.request.contextPath}/bonus" method="post">
                        <input type="hidden" name="action" value="delete">
                        <input type="hidden" name="id" value="<%= bonus.getId() %>">
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
