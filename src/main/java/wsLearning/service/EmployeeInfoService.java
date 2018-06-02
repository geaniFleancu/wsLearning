package wsLearning.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import wsLearning.exception.BadRequestException;
import wsLearning.model.EmployeeInfo;
import wsLearning.model.Requests.EmployeeInfoCreateRequest;
import wsLearning.model.Requests.EmployeeInfoUpdateRequest;
import wsLearning.repository.EmployeeInfoRepository;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EmployeeInfoService {

    @Autowired
    private EmployeeInfoRepository employeeInfoRepository;

    public Optional<EmployeeInfo> getEmployee(Integer employeeId) {
        return employeeInfoRepository.findById(employeeId);
    }

    public List<EmployeeInfo> getAllEmployees() {
        return (List<EmployeeInfo>) employeeInfoRepository.findAll();
    }

    public EmployeeInfo createEmployee(EmployeeInfoCreateRequest createEmployeeRequest) {
        String requestName = createEmployeeRequest.getName();
        Optional<EmployeeInfo> employeeId = getEmployee(createEmployeeRequest.getId());
        Optional<EmployeeInfo> employeeName = employeeInfoRepository.searchWithNativeQuery(requestName).stream().findFirst();
        if (employeeId.isPresent()) {
            throw new BadRequestException("employee.id.already.exists: " + getEmployee(createEmployeeRequest.getId()).toString());
        } else if (employeeName.isPresent()) {
            throw new BadRequestException("employee.name.already.exists: " + employeeInfoRepository.searchWithNativeQuery(requestName).toString());
        } else {

            EmployeeInfo employee = new EmployeeInfo();
            employee.setId(createEmployeeRequest.getId());
            employee.setEmployeeName(createEmployeeRequest.getName());
            employee.setEmployeeEmail(createEmployeeRequest.getEmail());
            employee.setEmployeeAge(createEmployeeRequest.getAge());
            return employeeInfoRepository.save(employee);
        }
    }

    public EmployeeInfo updateEmployee(EmployeeInfoUpdateRequest updateEmployeeRequest) {
        Optional<EmployeeInfo> employeeId = getEmployee(updateEmployeeRequest.getId());
        if (!employeeId.isPresent()) {
            throw new BadRequestException("employee.not.found");
        }
        EmployeeInfo employee = new EmployeeInfo();
        employee.setId(updateEmployeeRequest.getId());
        employee.setEmployeeName(updateEmployeeRequest.getName());
        employee.setEmployeeEmail(updateEmployeeRequest.getEmail());
        employee.setEmployeeAge(updateEmployeeRequest.getAge());
        return employeeInfoRepository.save(employee);
    }


    public void deleteEmployee(Integer employeeId) {
        Optional<EmployeeInfo> employee = getEmployee(employeeId);
        if (!employee.isPresent()) {
            throw new BadRequestException("employee.not.found");
        }
        employeeInfoRepository.deleteById(employeeId);
    }

    public List<EmployeeInfo> getAllEmployees(String employeeName, String employeeEmail) {
        if (!StringUtils.isEmpty(employeeName) && !StringUtils.isEmpty(employeeEmail)) {
            return employeeInfoRepository.findByEmployeeNameAndEmployeeEmail(employeeName, employeeEmail);
        } else if (!StringUtils.isEmpty(employeeName)) {
            return employeeInfoRepository.searchWithNativeQuery(employeeName);
        } else if (!StringUtils.isEmpty(employeeEmail)) {
            return employeeInfoRepository.findByEmployeeEmail(employeeEmail);
        }
        return Collections.emptyList();
    }
}
