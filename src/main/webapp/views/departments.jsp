<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="dto.DepartmentDTO" %>
<%@ page import="util.Escape" %>
<%@ include file="../layout/header.jsp" %>

<main class="departments-page">

    <section class="page-header">

        <div class="page-title">
            <h1>Departments</h1>
            <p>Manage company departments and employees.</p>
        </div>

        <div class="page-actions">
            <a href="${pageContext.request.contextPath}/views/add-department.jsp" class="btn-add">
                Add Department
            </a>
        </div>

    </section>

    <section class="department-grid">

        <%
            List<DepartmentDTO> departments = (List<DepartmentDTO>) request.getAttribute("departments");
            if (departments != null) {
                for (DepartmentDTO department : departments) {
        %>

        <div class="department-card">
            <h2><%= Escape.html(department.getName()) %></h2>
            <p>Department ID</p>
            <span><%= department.getId() %></span>
            <p>Employees</p>
            <span><%= department.getEmployeeCount() %></span>
            <div class="card-actions">
                <a href="${pageContext.request.contextPath}/department?id=<%=department.getId()%>" class="btn-view">
                    View
                </a>
                <form action="${pageContext.request.contextPath}/department" method="post" class="inline-form">
                    <input type="hidden" name="action" value="delete">
                    <input type="hidden" name="id" value="<%=department.getId()%>">
                    <button type="submit" class="btn-delete">Delete</button>
                </form>
            </div>
        </div>

        <%
                }
            }
        %>

    </section>

</main>

<%@ include file="../layout/footer.jsp" %>
