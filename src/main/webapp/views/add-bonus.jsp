<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="../layout/header.jsp" %>

<main class="add-bonus-page">

    <section class="page-header">

        <div class="page-title">

            <h1>
                Add New Bonus
            </h1>

            <p>
                Fill in the bonus information to create a new bonus record.
            </p>

        </div>

    </section>

    <section class="form-section">

        <div class="form-container">

            <form action="${pageContext.request.contextPath}/bonus" method="post">

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
                            Amount
                        </label>

                        <input type="number" step="0.01" name="amount" placeholder="Enter bonus amount" required>

                    </div>

                    <div class="form-group">

                        <label>
                            Reason
                        </label>

                        <input type="text" name="reason" placeholder="Enter reason for bonus" required>

                    </div>

                    <div class="form-group">

                        <label>
                            Bonus Date
                        </label>

                        <input type="date" name="bonusDate" required>

                    </div>

                </div>

                <div class="form-actions">

                    <button type="submit" class="btn-save">
                        Save Bonus
                    </button>

                    <a href="${pageContext.request.contextPath}/bonus" class="btn-cancel">
                        Cancel
                    </a>

                </div>

            </form>

        </div>

    </section>

</main>

<%@ include file="../layout/footer.jsp" %>
