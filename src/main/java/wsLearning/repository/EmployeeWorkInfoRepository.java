package wsLearning.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import wsLearning.model.EmployeeWorkInfo;

import java.util.List;
import java.util.Optional;

@Repository

public interface EmployeeWorkInfoRepository extends CrudRepository<EmployeeWorkInfo, Long> {

    @Query(value = "SELECT * FROM employeeportal.employeeworkinfo WHERE employeeName LIKE %:searchTerm%",
            nativeQuery = true)
    List<EmployeeWorkInfo> searchWithNativeQuery(@Param("searchTerm") String searchTerm);

    List<EmployeeWorkInfo> findByEmployeeNameAndEmployeeDepart(String employeeName, String employeeDepart);

    List<EmployeeWorkInfo> findByEmployeeName(String employeeName);

    List<EmployeeWorkInfo> findByEmployeeDepart(String employeeDepart);

    Optional<EmployeeWorkInfo> findById(Integer employeeId);

    void deleteById(Integer employeeId);
}
