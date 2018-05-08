package wsLearning.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import wsLearning.exception.BadRequestException;
import wsLearning.model.EmployeeInfo;
import wsLearning.model.Requests.EmployeeInfoCreateRequest;
import wsLearning.repository.EmployeeInfoRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeInfoRepository employeeInfoRepository;

    public Optional<EmployeeInfo> getEmployee(Long employeeId) {
        return employeeInfoRepository.findById(employeeId);
    }

    public List<EmployeeInfo> getAllEmployees() {
        return (List<EmployeeInfo>) employeeInfoRepository.findAll();
    }

    public EmployeeInfo createEmployee(EmployeeInfoCreateRequest createEmployeeRequest) {
        EmployeeInfo employee = new EmployeeInfo();
        employee.setEmployeeName(createEmployeeRequest.getName());
        employee.setEmployeeEmail(createEmployeeRequest.getEmail());
        employee.setEmployeeAge(createEmployeeRequest.getAge());
        return employeeInfoRepository.save(employee);
    }


    public void deleteEmployee(Long employeeId) {
        Optional<EmployeeInfo> client = getEmployee(employeeId);
        if (!client.isPresent()) {
            throw new BadRequestException("employee.not.found");
        }
        employeeInfoRepository.deleteById(employeeId);
    }

    public List<EmployeeInfo> getAllEmployees(String employeeName, String employeeEmail) {
        if (!StringUtils.isEmpty(employeeName) && !StringUtils.isEmpty(employeeEmail)) {
            return employeeInfoRepository.findByEmployeeNameAndEmployeeEmail(employeeName, employeeEmail);
        } else if (!StringUtils.isEmpty(employeeName)) {
            return employeeInfoRepository.findByEmployeeName(employeeName);
        } else if (!StringUtils.isEmpty(employeeEmail)) {
            return employeeInfoRepository.findByEmployeeEmail(employeeEmail);
        }
        return Collections.emptyList();
    }


}
