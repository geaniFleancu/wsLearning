package wsLearning.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import wsLearning.model.EmployeeInfo;

import java.util.List;

@Repository
public interface EmployeeInfoRepository extends CrudRepository<EmployeeInfo, Long> {

    List<EmployeeInfo> findByEmployeeNameAndEmployeeEmail(String employeeName, String employeeEmail);

    List<EmployeeInfo> findByEmployeeName(String employeeName);

    List<EmployeeInfo> findByEmployeeEmail(String employeeEmail);


}
