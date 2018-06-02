package wsLearning.model;

import javax.persistence.*;

@Entity // This tells Hibernate to make a table out of this class
@Table(name = "employeeworkinfo")

public class EmployeeWorkInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    //@Column(name = "id")
    private Integer id;

    @Column(name = "employeeName")
    private String employeeName;
    @Column(name = "departName")
    private String employeeDepart;
    @Column(name = "departFunction")
    private String employeeFunction;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeFunction() {
        return employeeFunction;
    }

    public void setEmployeeFunction(String employeeFunction) {
        this.employeeFunction = employeeFunction;
    }

    public String getEmployeeDepart() {
        return employeeDepart;
    }

    public void setEmployeeDepart(String employeeDepart) {
        this.employeeDepart = employeeDepart;
    }


    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", employeeName='" + employeeName + '\'' +
                ", employeeFunction='" + employeeFunction + '\'' +
                ", employeeDepart='" + employeeDepart + '\'' +
                '}';
    }
}

