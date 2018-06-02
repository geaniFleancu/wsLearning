package wsLearning.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity // This tells Hibernate to make a table out of this class
@Table(name = "employeeinfo")
@SecondaryTables({
        @SecondaryTable(name = "employeeworkinfo", pkJoinColumns = {
                @PrimaryKeyJoinColumn(name = "id", referencedColumnName = "id")}),
        @SecondaryTable(name = "employeeworkprojects", pkJoinColumns = {
                @PrimaryKeyJoinColumn(name = "id", referencedColumnName = "id")})
})

public class Employee {

    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    @GenericGenerator(name = "UseExistingIdOtherwiseGenerateUsingIdentity", strategy = "wsLearning.model.UseExistingIdOtherwiseGenerateUsingIdentity")
    @GeneratedValue(generator = "UseExistingIdOtherwiseGenerateUsingIdentity")
    @Column(name = "id")
    private Integer id;

    @JoinColumn(name = "employeeName")
    private String employeeName;

    @Column(table = "employeeinfo", name = "age")
    private Integer employeeAge;
    @Column(table = "employeeinfo", name = "email")
    private String employeeEmail;
    @Column(table = "employeeworkinfo", name = "departName")
    private String employeeDepart;
    @Column(table = "employeeworkinfo", name = "departFunction")
    private String employeeFunction;
    @Column(table = "employeeworkprojects", name = "projectName")
    private String employeeProject;


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

    public String getEmployeeEmail() {
        return employeeEmail;
    }

    public void setEmployeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
    }

    public Integer getEmployeeAge() {
        return employeeAge;
    }

    public void setEmployeeAge(Integer employeeAge) {
        this.employeeAge = employeeAge;
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


    public String getEmployeeProject() {
        return employeeProject;
    }

    public void setEmployeeProject(String employeeProject) {
        this.employeeProject = employeeProject;
    }


    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", employeeName='" + employeeName + '\'' +
                ", employeeEmail='" + employeeEmail + '\'' +
                ", employeeAge='" + employeeAge + '\'' +
                ", employeeFunction='" + employeeFunction + '\'' +
                ", employeeDepart='" + employeeDepart + '\'' +
                ", employeeProject='" + employeeProject + '\'' +
                '}';
    }
}
