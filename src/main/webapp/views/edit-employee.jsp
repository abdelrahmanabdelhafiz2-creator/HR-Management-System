<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ page import="java.util.List" %>
<%@ page import="dto.DepartmentDTO" %>
<%@ page import="dto.EmployeeDTO" %>
<%@ page import="util.Escape" %>

<%@ include file="../layout/header.jsp" %>

<main class="add-employee-page">

    <section class="page-header">

        <div class="page-title">

            <h1>
                Edit Employee
            </h1>

            <p>
                Update employee information.
            </p>

        </div>

    </section>

    <%
        EmployeeDTO emp = (EmployeeDTO) request.getAttribute("employee");
        List<DepartmentDTO> departments = (List<DepartmentDTO>) request.getAttribute("departments");
        List<EmployeeDTO> managers = (List<EmployeeDTO>) request.getAttribute("managers");
    %>

    <% if (emp != null) { %>

    <section class="form-section">

        <div class="form-container">

            <form action="${pageContext.request.contextPath}/employee" method="post">

                <input type="hidden" name="action" value="update">

                <input type="hidden" name="id" value="<%= emp.getId() %>">

                <div class="form-grid">

                    <div class="form-group">

                        <label>
                            Full Name
                        </label>

                        <input type="text" name="name" value="<%= Escape.html(emp.getName()) %>" required>

                    </div>

                    <div class="form-group">

                        <label>
                            Email
                        </label>

                        <input type="email" name="email" value="<%= Escape.html(emp.getEmail()) %>" required>

                    </div>

                    <div class="form-group">

                        <label>
                            Password
                        </label>

                        <input type="password" name="password" placeholder="Leave empty to keep current password">

                    </div>

                    <div class="form-group">

                        <label>
                            Salary
                        </label>

                        <input type="number" step="0.01" name="salary" value="<%= emp.getSalary() %>" required>

                    </div>

                    <div class="form-group">

                        <label>
                            Role
                        </label>

                        <select name="role">

                            <%
                                String[] roles = {
                                        "Backend Developer", "Frontend Developer",
                                        "HR Specialist", "Accountant", "Team Leader"
                                };
                                String currentRole = emp.getRole() == null ? "" : emp.getRole();
                                boolean matchedKnownRole = false;
                                for (String role : roles) {
                                    if (role.equals(currentRole)) {
                                        matchedKnownRole = true;
                                    }
                                }
                                if (!matchedKnownRole && !currentRole.isEmpty()) {
                            %>
                            <option value="<%= Escape.html(currentRole) %>" selected>
                                <%= Escape.html(currentRole) %>
                            </option>
                            <%
                                }
                                for (String role : roles) {
                            %>
                            <option value="<%= role %>" <%= role.equals(currentRole) ? "selected" : "" %>>
                                <%= role %>
                            </option>
                            <%
                                }
                            %>

                        </select>

                    </div>

                    <div class="form-group">

                        <label>
                            Department
                        </label>

                        <select name="departmentId" required>

                            <option value="">
                                Select Department
                            </option>

                            <%
                                if (departments != null) {
                                    for (DepartmentDTO dept : departments) {
                            %>
                            <option value="<%= dept.getId() %>" <%= dept.getId() == emp.getDepartmentId() ? "selected" : "" %>>
                                <%= Escape.html(dept.getName()) %>
                            </option>
                            <%
                                    }
                                }
                            %>

                        </select>

                    </div>

                    <div class="form-group">

                        <label>
                            Manager
                        </label>

                        <select name="managerId">

                            <option value="0" <%= emp.getManagerId() == null ? "selected" : "" %>>
                                No Manager
                            </option>

                            <%
                                if (managers != null) {
                                    for (EmployeeDTO mgr : managers) {
                                        if (mgr.getId() == emp.getId()) {
                                            // موظف ميختارش نفسه كمدير ليه
                                            continue;
                                        }
                                        boolean selected = emp.getManagerId() != null && emp.getManagerId() == mgr.getId();
                            %>
                            <option value="<%= mgr.getId() %>" <%= selected ? "selected" : "" %>>
                                <%= Escape.html(mgr.getName()) %>
                            </option>
                            <%
                                    }
                                }
                            %>

                        </select>

                    </div>

                </div>

                <div class="form-actions">

                    <button type="submit" class="btn-save">
                        Update Employee
                    </button>

                    <a href="${pageContext.request.contextPath}/employee" class="btn-cancel">
                        Cancel
                    </a>

                </div>

            </form>

        </div>

    </section>

    <% } %>

</main>

<%@ include file="../layout/footer.jsp" %>
