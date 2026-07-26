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
                Add New Employee
            </h1>

            <p>
                Fill in the employee information to create a new employee record.
            </p>

        </div>

    </section>

    <%
        List<DepartmentDTO> departments = (List<DepartmentDTO>) request.getAttribute("departments");
        List<EmployeeDTO> managers = (List<EmployeeDTO>) request.getAttribute("managers");
    %>

    <section class="form-section">

        <div class="form-container">

            <form action="${pageContext.request.contextPath}/employee" method="post">

                <input type="hidden" name="action" value="save">

                <div class="form-grid">

                    <div class="form-group">

                        <label>
                            Full Name
                        </label>

                        <input type="text" name="name" placeholder="Enter employee full name" required>

                    </div>

                    <div class="form-group">

                        <label>
                            Email Address
                        </label>

                        <input type="email" name="email" placeholder="Enter email" required>

                    </div>

                    <div class="form-group">

                        <label>
                            Password
                        </label>

                        <input type="password" name="password" placeholder="Enter password" required>

                    </div>

                    <div class="form-group">

                        <label>
                            Salary
                        </label>

                        <input type="number" name="salary" placeholder="Enter salary" required>

                    </div>

                    <div class="form-group">

                        <label>
                            Role
                        </label>

                        <select name="role" required>

                            <option value="">
                                Select Role
                            </option>

                            <option value="Backend Developer">
                                Backend Developer
                            </option>

                            <option value="Frontend Developer">
                                Frontend Developer
                            </option>

                            <option value="HR Specialist">
                                HR Specialist
                            </option>

                            <option value="Accountant">
                                Accountant
                            </option>

                            <option value="Team Leader">
                                Team Leader
                            </option>

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
                            <option value="<%= dept.getId() %>">
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

                            <option value="0">
                                No Manager
                            </option>

                            <%
                                if (managers != null) {
                                    for (EmployeeDTO mgr : managers) {
                            %>
                            <option value="<%= mgr.getId() %>">
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
                        Save Employee
                    </button>

                    <a href="${pageContext.request.contextPath}/employee" class="btn-cancel">
                        Cancel
                    </a>

                </div>

            </form>

        </div>

    </section>

</main>

<%@ include file="../layout/footer.jsp" %>
