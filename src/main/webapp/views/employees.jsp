<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="dto.EmployeeDTO" %>
<%@ page import="util.Escape" %>
<%@ include file="../layout/header.jsp" %>

<main class="employees-page">
    <section class="page-header">
        <div class="page-title">
            <h1>Employees</h1>
            <p>Manage all employees in the company.</p>
        </div>
        <a href="${pageContext.request.contextPath}/employee?action=new" class="btn-add">Add Employee</a>
    </section>

    <div class="table-wrapper">
        <table>
            <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Email</th>
                <th>Role</th>
                <th>Department</th>
                <th>Manager</th>
                <th>Salary</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <%
                List<EmployeeDTO> employees = (List<EmployeeDTO>) request.getAttribute("employees");
                if (employees != null) {
                    for (EmployeeDTO emp : employees) {
            %>
            <tr>
                <td><%= emp.getId() %></td>
                <td><%= Escape.html(emp.getName()) %></td>
                <td><%= Escape.html(emp.getEmail()) %></td>
                <td><%= Escape.html(emp.getRole()) %></td>
                <td><%= emp.getDepartmentName() != null ? Escape.html(emp.getDepartmentName()) : "-" %></td>
                <td><%= emp.getManagerName() != null ? Escape.html(emp.getManagerName()) : "None" %></td>
                <td><%= emp.getSalary() %></td>
                <td>
                    <a href="${pageContext.request.contextPath}/employee?action=view&id=<%=emp.getId()%>" class="btn-view">View</a>
                    <form action="${pageContext.request.contextPath}/employee" method="post" class="inline-form">
                        <input type="hidden" name="action" value="delete">
                        <input type="hidden" name="id" value="<%=emp.getId()%>">
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
