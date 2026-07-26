package dto;

public class EmployeeDTO {

    private int id;
    private String name;
    private String email;
    private String password;
    private double salary;
    private String role;
    private int departmentId;
    private Integer managerId;

    // مش عمود في جدول employee - بيتملى من الـ JOIN مع جدول department
    private String departmentName;

    // مش عمود في جدول employee - بيتملى من الـ JOIN مع الموظف صاحب managerId
    private String managerName;

    public EmployeeDTO() {
    }

    public EmployeeDTO(int id, String name, String email, String password,
                       double salary, String role,
                       int departmentId, Integer managerId) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.salary = salary;
        this.role = role;
        this.departmentId = departmentId;
        this.managerId = managerId;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }
}
