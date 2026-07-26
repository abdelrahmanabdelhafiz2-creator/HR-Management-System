<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="../layout/header.jsp" %>

<main class="add-promotion-page">

    <section class="page-header">

        <div class="page-title">

            <h1>
                Add New Promotion
            </h1>

            <p>
                Fill in the promotion information to create a new promotion record.
            </p>

        </div>

    </section>

    <section class="form-section">

        <div class="form-container">

            <form action="${pageContext.request.contextPath}/promotion" method="post">

                <input type="hidden" name="action" value="save">

                <div class="form-grid">

                    <div class="form-group">

                        <label>
                            Employee ID
                        </label>

                        <input type="number" name="employeeId" placeholder="Enter employee ID" required>

                    </div>

                    <div class="form-group">

                        <label>
                            Old Role
                        </label>

                        <input type="text" name="oldRole" placeholder="Enter old role" required>

                    </div>

                    <div class="form-group">

                        <label>
                            New Role
                        </label>

                        <input type="text" name="newRole" placeholder="Enter new role" required>

                    </div>

                    <div class="form-group">

                        <label>
                            Old Salary
                        </label>

                        <input type="number" step="0.01" name="oldSalary" placeholder="Enter old salary" required>

                    </div>

                    <div class="form-group">

                        <label>
                            New Salary
                        </label>

                        <input type="number" step="0.01" name="newSalary" placeholder="Enter new salary" required>

                    </div>

                    <div class="form-group">

                        <label>
                            Promotion Date
                        </label>

                        <input type="date" name="promotionDate" required>

                    </div>

                </div>

                <div class="form-actions">

                    <button type="submit" class="btn-save">
                        Save Promotion
                    </button>

                    <a href="${pageContext.request.contextPath}/promotion" class="btn-cancel">
                        Cancel
                    </a>

                </div>

            </form>

        </div>

    </section>

</main>

<%@ include file="../layout/footer.jsp" %>
