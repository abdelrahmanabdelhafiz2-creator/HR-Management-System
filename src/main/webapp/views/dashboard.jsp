<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ page import="java.util.List" %>
<%@ page import="dto.EmployeeDTO" %>

<%@ include file="../layout/header.jsp" %>

<main class="dashboard-page">

    <section class="dashboard-header">

        <div class="page-title">

            <h1>
                Dashboard
            </h1>

            <p>
                Welcome back, HR Manager.
                Here is a quick overview of your organization.
            </p>

        </div>

    </section>

    <section class="statistics-section">

        <div class="stats-grid">

            <div class="stat-card">

                <h3>
                    Total Employees
                </h3>

                <span class="stat-number">
                    <%= request.getAttribute("employeeCount") %>
                </span>

            </div>

            <div class="stat-card">

                <h3>
                    Total Departments
                </h3>

                <span class="stat-number">
                    <%= request.getAttribute("departmentCount") %>
                </span>

            </div>

            <div class="stat-card">

                <h3>
                    Total Promotions
                </h3>

                <span class="stat-number">
                    <%= request.getAttribute("promotionCount") %>
                </span>

            </div>

            <div class="stat-card">

                <h3>
                    Total Bonuses
                </h3>

                <span class="stat-number">
                    <%= request.getAttribute("bonusCount") %>
                </span>

            </div>

        </div>

    </section>

    <section class="employee-overview">

        <div class="section-header">

            <h2>
                Employee Overview
            </h2>

        </div>

        <div class="table-wrapper">

            <table>

                <thead>

                <tr>

                    <th>ID</th>

                    <th>Name</th>

                    <th>Role</th>

                    <th>Department</th>

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

                    <td>
                        <%= emp.getId() %>
                    </td>

                    <td>
                        <%= emp.getName() %>
                    </td>

                    <td>
                        <%= emp.getRole() %>
                    </td>

                    <td>
                        <%= emp.getDepartmentId() %>
                    </td>

                    <td>
                        <%= emp.getSalary() %>
                    </td>

                    <td>

                        <a class="btn-view"
                           href="${pageContext.request.contextPath}/employee?action=view&id=<%=emp.getId()%>">
                            View
                        </a>

                        <form action="${pageContext.request.contextPath}/employee"
                              method="post"
                              class="inline-form">

                            <input type="hidden"
                                   name="action"
                                   value="delete">

                            <input type="hidden"
                                   name="id"
                                   value="<%=emp.getId()%>">

                            <button type="submit"
                                    class="btn-delete">
                                Delete
                            </button>

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

    </section>

</main>

<%@ include file="../layout/footer.jsp" %>
