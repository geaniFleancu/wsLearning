package wsLearning.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import wsLearning.model.EmployeeInfo;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeInfoRepository extends CrudRepository<EmployeeInfo, Long> {

    List<EmployeeInfo> findByEmployeeNameAndEmployeeEmail(String employeeName, String employeeEmail);

    List<EmployeeInfo> findByEmployeeName(String employeeName);

    List<EmployeeInfo> findByEmployeeEmail(String employeeEmail);

    Optional<EmployeeInfo> findById(Integer employeeId);

    void deleteById(Integer employeeId);
}
