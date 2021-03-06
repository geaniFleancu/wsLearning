package wsLearning.model.Requests;

public class EmployeeInfoUpdateRequest {

    private Integer employeeId;

    private String employeeName;

    private Integer employeeAge;

    private String employeeEmail;

    public Integer getId() {
        return employeeId;
    }

    public void setId(Integer employeeId) {
        this.employeeId = employeeId;
    }

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
