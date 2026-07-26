package dto;

import java.sql.Date;

public class BonusDTO {

    private int id;
    private int employeeId;
    private double amount;
    private String reason;
    private Date bonusDate;

    // مش أعمدة في جدول bonus - بتتملى من الـ JOIN مع employee و department
    private String employeeName;
    private String departmentName;

    public BonusDTO() {
    }

    public BonusDTO(int id, int employeeId, double amount, String reason, Date bonusDate) {
        this.id = id;
        this.employeeId = employeeId;
        this.amount = amount;
        this.reason = reason;
        this.bonusDate = bonusDate;
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

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Date getBonusDate() {
        return bonusDate;
    }

    public void setBonusDate(Date bonusDate) {
        this.bonusDate = bonusDate;
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
