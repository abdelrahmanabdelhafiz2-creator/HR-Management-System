<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ page import="dto.EmployeeDTO" %>
<%@ page import="util.Escape" %>

<%@ include file="../layout/header.jsp" %>


<main class="employee-details-page">


    <section class="page-header">


        <div class="page-title">


            <h1>
                Employee Details
            </h1>


            <p>
                View employee profile, promotions, bonuses, and feedback history.
            </p>


        </div>


    </section>





    <%

        EmployeeDTO emp =
                (EmployeeDTO) request.getAttribute("employee");


        if(emp != null){

    %>





    <section class="profile-section">


        <div class="profile-card">


            <div class="profile-header">

                <h2>
                    Employee Profile
                </h2>

            </div>





            <div class="profile-grid">



                <div class="profile-item">

                    <label>Name</label>

                    <span>
                        <%= Escape.html(emp.getName()) %>
                    </span>

                </div>





                <div class="profile-item">

                    <label>Email</label>

                    <span>
                        <%= Escape.html(emp.getEmail()) %>
                    </span>

                </div>





                <div class="profile-item">

                    <label>Role</label>

                    <span>
                        <%= Escape.html(emp.getRole()) %>
                    </span>

                </div>





                <div class="profile-item">

                    <label>Department</label>

                    <span>
                        <%= emp.getDepartmentName() != null ? Escape.html(emp.getDepartmentName()) : "-" %>
                    </span>

                </div>





                <div class="profile-item">

                    <label>Salary</label>

                    <span>
                        <%= emp.getSalary() %>
                    </span>

                </div>





                <div class="profile-item">

                    <label>Manager</label>

                    <span>
                        <%= emp.getManagerName() != null ? Escape.html(emp.getManagerName()) : "None" %>
                    </span>

                </div>



            </div>





            <div class="profile-actions">



                <a href="${pageContext.request.contextPath}/employee?action=edit&id=<%=emp.getId()%>"
                   class="btn-edit">

                    Edit Employee

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


                        Delete Employee


                    </button>


                </form>



            </div>



        </div>



    </section>





    <%

        }

    %>





</main>



<%@ include file="../layout/footer.jsp" %>
