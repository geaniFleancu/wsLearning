package wsLearning.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import wsLearning.model.Employee;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    @Query(value = "SELECT * FROM employeeportal.employeeinfo WHERE employeeName LIKE %:searchTerm%",
            nativeQuery = true)
    List<Employee> searchWithNativeQuery(@Param("searchTerm") String searchTerm);

    List<Employee> findByEmployeeNameAndEmployeeEmail(String employeeName, String employeeEmail);

    List<Employee> findByEmployeeName(String employeeName);

    List<Employee> findByEmployeeEmail(String employeeEmail);

    Optional<Employee> findById(Integer employeeId);

    void deleteById(Integer employeeId);
}
