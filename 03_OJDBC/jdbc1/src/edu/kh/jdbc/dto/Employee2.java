package edu.kh.jdbc.dto;

public class Employee2 {
    private String departmentTitle;
    private String jobName;
    private String empName;
    private String email;

    public Employee2() {
    }

    public Employee2(String departmentTitle, String jobName, String empName, String email) {
        this.departmentTitle = departmentTitle;
        this.jobName = jobName;
        this.empName = empName;
        this.email = email;
    }

    public String getDepartmentTitle() {
        return departmentTitle;
    }

    public void setDepartmentTitle(String departmentTitle) {
        this.departmentTitle = departmentTitle;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return String.format("%s / %s / %s / %s", departmentTitle, jobName, empName, email);
    }
}
