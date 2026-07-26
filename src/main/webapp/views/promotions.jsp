<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="dto.PromotionDTO" %>
<%@ page import="util.Escape" %>
<%@ include file="../layout/header.jsp" %>

<main class="promotions-page">

  <section class="page-header">

    <div class="page-title">
      <h1>Promotions</h1>
      <p>Employee promotion history.</p>
    </div>

    <a href="${pageContext.request.contextPath}/views/add-promotion.jsp" class="btn-add">
      Add Promotion
    </a>

  </section>

  <div class="table-wrapper">
    <table>
      <thead>
      <tr>
        <th>Employee</th>
        <th>Department</th>
        <th>Old Role</th>
        <th>New Role</th>
        <th>Old Salary</th>
        <th>New Salary</th>
        <th>Date</th>
        <th>Action</th>
      </tr>
      </thead>
      <tbody>
      <%
        List<PromotionDTO> promotions = (List<PromotionDTO>) request.getAttribute("promotions");
        if (promotions != null) {
          for (PromotionDTO promotion : promotions) {
      %>
      <tr>
        <td><%= promotion.getEmployeeName() != null ? Escape.html(promotion.getEmployeeName()) : "#" + promotion.getEmployeeId() %></td>
        <td><%= promotion.getDepartmentName() != null ? Escape.html(promotion.getDepartmentName()) : "-" %></td>
        <td><%= Escape.html(promotion.getOldRole()) %></td>
        <td><%= Escape.html(promotion.getNewRole()) %></td>
        <td><%= promotion.getOldSalary() %></td>
        <td><%= promotion.getNewSalary() %></td>
        <td><%= promotion.getPromotionDate() %></td>
        <td>
          <form action="${pageContext.request.contextPath}/promotion" method="post">
            <input type="hidden" name="action" value="delete">
            <input type="hidden" name="id" value="<%= promotion.getId() %>">
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
