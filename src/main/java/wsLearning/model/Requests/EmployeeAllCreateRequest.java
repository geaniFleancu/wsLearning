package wsLearning.model.Requests;

public class EmployeeAllCreateRequest {

    private Integer employeeId;

    private String employeeName;

    private Integer employeeAge;

    private String employeeEmail;

    private String employeeDepart;

    private String employeeFunction;

    private String employeeProject;

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

    public String getDepart() {
        return employeeDepart;
    }

    public void setDepart(String employeeDepart) {
        this.employeeDepart = employeeDepart;
    }

    public String getFunction() {
        return employeeFunction;
    }

    public void setFunction(String employeeFunction) {
        this.employeeFunction = employeeFunction;
    }

    public String getProject() {
        return employeeProject;
    }

    public void setProject(String employeeProject) {
        this.employeeProject = employeeProject;
    }
}
