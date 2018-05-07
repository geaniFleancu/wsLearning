package wsLearning;

import org.springframework.data.repository.CrudRepository;
import wsLearning.EmployeeInfo;

// This will be AUTO IMPLEMENTED by Spring into a Bean called employeeInfoRepository
// CRUD refers Create, Read, Update, Delete

public interface EmployeeInfoRepository extends CrudRepository<EmployeeInfo, Long> {

}
