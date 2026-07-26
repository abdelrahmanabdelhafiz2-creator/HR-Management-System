package dto;

import java.sql.Date;

public class FeedbackDTO {

    private int id;
    private int employeeId;
    private int rate;
    private String comment;
    private Date feedbackDate;

    // مش أعمدة في جدول feedback - بتتملى من الـ JOIN مع employee و department
    private String employeeName;
    private String departmentName;

    public FeedbackDTO() {
    }

    public FeedbackDTO(int id, int employeeId, int rate,
                       String comment, Date feedbackDate) {
        this.id = id;
        this.employeeId = employeeId;
        this.rate = rate;
        this.comment = comment;
        this.feedbackDate = feedbackDate;
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

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getFeedbackDate() {
        return feedbackDate;
    }

    public void setFeedbackDate(Date feedbackDate) {
        this.feedbackDate = feedbackDate;
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
