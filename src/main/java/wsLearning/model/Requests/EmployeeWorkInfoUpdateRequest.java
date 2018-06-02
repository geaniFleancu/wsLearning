package wsLearning.model.Requests;

public class EmployeeWorkInfoUpdateRequest {

    private int employeeId;

    private String employeeName;

    private String employeeDepart;

    private String employeeFunction;

    public int getId() {
        return employeeId;
    }

    public void setId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return employeeName;
    }

    public void setName(String employeeName) {
        this.employeeName = employeeName;
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
}
