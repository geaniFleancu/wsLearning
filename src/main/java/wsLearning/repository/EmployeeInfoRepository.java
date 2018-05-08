package wsLearning.repository;

import org.springframework.data.repository.CrudRepository;
import wsLearning.model.EmployeeInfo;

import java.util.List;

// This will be AUTO IMPLEMENTED by Spring into a Bean called employeeInfoRepository
// CRUD refers Create, Read, Update, Delete

public interface EmployeeInfoRepository extends CrudRepository<EmployeeInfo, Long> {

    List<EmployeeInfo> findByEmployeeNameAndEmail(String employeeName, String employeeEmail);

    List<EmployeeInfo> findByEmployeeName(String employeeName);

    List<EmployeeInfo> findByEmployeeEmail(String employeeEmail);


}
