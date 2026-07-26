package dto;

import java.sql.Date;

public class PromotionDTO {

    private int id;
    private int employeeId;
    private String oldRole;
    private String newRole;
    private double oldSalary;
    private double newSalary;
    private Date promotionDate;

    // مش أعمدة في جدول promotion - بتتملى من الـ JOIN مع employee و department
    private String employeeName;
    private String departmentName;

    public PromotionDTO() {
    }

    public PromotionDTO(int id, int employeeId, String oldRole, String newRole,
                        double oldSalary, double newSalary, Date promotionDate) {
        this.id = id;
        this.employeeId = employeeId;
        this.oldRole = oldRole;
        this.newRole = newRole;
        this.oldSalary = oldSalary;
        this.newSalary = newSalary;
        this.promotionDate = promotionDate;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getOldRole() {
        return oldRole;
    }

    public void setOldRole(String oldRole) {
        this.oldRole = oldRole;
    }

    public String getNewRole() {
        return newRole;
    }

    public void setNewRole(String newRole) {
        this.newRole = newRole;
    }

    public double getOldSalary() {
        return oldSalary;
    }

    public void setOldSalary(double oldSalary) {
        this.oldSalary = oldSalary;
    }

    public double getNewSalary() {
        return newSalary;
    }

    public void setNewSalary(double newSalary) {
        this.newSalary = newSalary;
    }

    public Date getPromotionDate() {
        return promotionDate;
    }

    public void setPromotionDate(Date promotionDate) {
        this.promotionDate = promotionDate;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
}
