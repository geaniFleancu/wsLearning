package wsLearning.model.Requests;

public class EmployeeInfoCreateRequest {

    private String employeeName;

    private Integer employeeAge;

    private String employeeEmail;

    public String getName() {
        return employeeName;
    }

    public void setName(String employeeName) {
        this.employeeName = employeeName;
    }

    public Integer getAge() {
        return employeeAge;
    }

    public void setAge(Integer employeeAge) {
        this.employeeAge = employeeAge;
    }

    public String getEmail() {
        return employeeEmail;
    }

    public void setEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
    }
}
