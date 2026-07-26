<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="../layout/header.jsp" %>

<main class="add-department-page">

    <section class="page-header">

        <div class="page-title">

            <h1>
                Add New Department
            </h1>

            <p>
                Fill in the department name to create a new department record.
            </p>

        </div>

    </section>

    <section class="form-section">

        <div class="form-container">

            <form action="${pageContext.request.contextPath}/department" method="post">

                <input type="hidden" name="action" value="save">

                <div class="form-grid">

                    <div class="form-group">

                        <label>
                            Department Name
                        </label>

                        <input type="text" name="name" placeholder="Enter department name" required>

                    </div>

                </div>

                <div class="form-actions">

                    <button type="submit" class="btn-save">
                        Save Department
                    </button>

                    <a href="${pageContext.request.contextPath}/department" class="btn-cancel">
                        Cancel
                    </a>

                </div>

            </form>

        </div>

    </section>

</main>

<%@ include file="../layout/footer.jsp" %>
