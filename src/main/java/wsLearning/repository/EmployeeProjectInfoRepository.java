package wsLearning.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import wsLearning.model.EmployeeProjectInfo;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeProjectInfoRepository extends CrudRepository<EmployeeProjectInfo, Long> {

    @Query(value = "SELECT * FROM employeeportal.employeeworkprojects WHERE employeeName LIKE %:searchTerm%",
            nativeQuery = true)
    List<EmployeeProjectInfo> searchWithNativeQuery(@Param("searchTerm") String searchTerm);

    List<EmployeeProjectInfo> findByEmployeeNameAndEmployeeProject(String employeeName, String employeeProject);

    List<EmployeeProjectInfo> findByEmployeeName(String employeeName);

    List<EmployeeProjectInfo> findByEmployeeProject(String employeeProject);

    Optional<EmployeeProjectInfo> findById(Integer employeeId);

    void deleteById(Integer employeeId);
}
