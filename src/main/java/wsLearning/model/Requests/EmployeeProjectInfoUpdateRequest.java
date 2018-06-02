package wsLearning.model.Requests;

public class EmployeeProjectInfoUpdateRequest {

    private Integer employeeId;

    private String employeeName;

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

    public String getProject() {
        return employeeProject;
    }

    public void setProject(String employeeProject) {
        this.employeeProject = employeeProject;
    }

}
