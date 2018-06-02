package wsLearning.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import wsLearning.exception.BadRequestException;
import wsLearning.model.Employee;
import wsLearning.model.Requests.EmployeeAllCreateRequest;
import wsLearning.model.Requests.EmployeeAllUpdateRequest;
import wsLearning.repository.EmployeeRepository;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public Optional<Employee> getEmployee(Integer employeeId) {
        return employeeRepository.findById(employeeId);
    }

    public List<Employee> getAllEmployees() {
        return (List<Employee>) employeeRepository.findAll();
    }

    public Employee createEmployee(EmployeeAllCreateRequest createEmployeeRequest) {


        Employee employee = new Employee();
        employee.setId(createEmployeeRequest.getId());
        employee.setEmployeeName(createEmployeeRequest.getName());
        employee.setEmployeeEmail(createEmployeeRequest.getEmail());
        employee.setEmployeeAge(createEmployeeRequest.getAge());
        employee.setEmployeeFunction(createEmployeeRequest.getFunction());
        employee.setEmployeeDepart(createEmployeeRequest.getDepart());
        employee.setEmployeeProject(createEmployeeRequest.getProject());
        return employee;

    }

    public Employee updateEmployee(EmployeeAllUpdateRequest updateEmployeeRequest) {
        Optional<Employee> employeeId = getEmployee(updateEmployeeRequest.getId());
        if (!employeeId.isPresent()) {
            throw new BadRequestException("employee.not.found");
        }
        Employee employee = new Employee();
        employee.setId(updateEmployeeRequest.getId());
        employee.setEmployeeName(updateEmployeeRequest.getName());
        employee.setEmployeeEmail(updateEmployeeRequest.getEmail());
        employee.setEmployeeAge(updateEmployeeRequest.getAge());
        employee.setEmployeeFunction(updateEmployeeRequest.getFunction());
        employee.setEmployeeDepart(updateEmployeeRequest.getDepart());
        employee.setEmployeeProject(updateEmployeeRequest.getProject());
        return employeeRepository.save(employee);
    }


    public void deleteEmployee(Integer employeeId) {
        Optional<Employee> employee = getEmployee(employeeId);
        if (!employee.isPresent()) {
            throw new BadRequestException("employee.not.found");
        }
        employeeRepository.deleteById(employeeId);
    }

    public List<Employee> getAllEmployees(String employeeName, String employeeEmail) {
        if (!StringUtils.isEmpty(employeeName) && !StringUtils.isEmpty(employeeEmail)) {
            return employeeRepository.findByEmployeeNameAndEmployeeEmail(employeeName, employeeEmail);
        } else if (!StringUtils.isEmpty(employeeName)) {
            return employeeRepository.searchWithNativeQuery(employeeName);
        } else if (!StringUtils.isEmpty(employeeEmail)) {
            return employeeRepository.findByEmployeeEmail(employeeEmail);
        }
        return Collections.emptyList();
    }
}
