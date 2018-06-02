package wsLearning.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity // This tells Hibernate to make a table out of this class
@Table(name = "employeeworkprojects")

public class EmployeeProjectInfo {

    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    @GenericGenerator(name = "UseExistingIdOtherwiseGenerateUsingIdentity", strategy = "wsLearning.model.UseExistingIdOtherwiseGenerateUsingIdentity")
    @GeneratedValue(generator = "UseExistingIdOtherwiseGenerateUsingIdentity")
    @Column(name = "id")
    private Integer id;

    @Column(name = "employeeName")
    private String employeeName;
    @Column(name = "projectName")
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

    public String getEmployeeProject() {
        return employeeProject;
    }

    public void setEmployeeProject(String employeeProject) {
        this.employeeProject = employeeProject;
    }


    @Override
    public String toString() {
        return "Employee project info{" +
                "id=" + id +
                ", employeeName='" + employeeName + '\'' +
                ", employeeProject='" + employeeProject + '\'' +
                '}';
    }
}

