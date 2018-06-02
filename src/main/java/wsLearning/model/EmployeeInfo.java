package wsLearning.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity // This tells Hibernate to make a table out of this class
@Table(name = "employeeinfo")
public class EmployeeInfo {

    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    @GenericGenerator(name = "UseExistingIdOtherwiseGenerateUsingIdentity", strategy = "wsLearning.model.UseExistingIdOtherwiseGenerateUsingIdentity")
    @GeneratedValue(generator = "UseExistingIdOtherwiseGenerateUsingIdentity")
    @Column(name = "id")
    private Integer id;

    @Column(name = "employeeName")
    private String employeeName;
    @Column(name = "age")
    private Integer employeeAge;
    @Column(name = "email")
    private String employeeEmail;


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


    @Override
    public String toString() {
        return "Employee info{" +
                "id=" + id +
                ", employeeName='" + employeeName + '\'' +
                ", employeeEmail='" + employeeEmail + '\'' +
                ", employeeAge='" + employeeAge + '\'' +
                '}';
    }
}
