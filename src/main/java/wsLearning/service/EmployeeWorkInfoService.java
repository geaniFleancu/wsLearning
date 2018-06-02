package wsLearning.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import wsLearning.exception.BadRequestException;
import wsLearning.model.EmployeeWorkInfo;
import wsLearning.model.Requests.EmployeeWorkInfoCreateRequest;
import wsLearning.model.Requests.EmployeeWorkInfoUpdateRequest;
import wsLearning.repository.EmployeeWorkInfoRepository;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EmployeeWorkInfoService {

    @Autowired
    private EmployeeWorkInfoRepository employeeWorkInfoRepository;

    public Optional<EmployeeWorkInfo> getEmployeeWorkInfo(Integer employeeId) {
        return employeeWorkInfoRepository.findById(employeeId);
    }

    public List<EmployeeWorkInfo> getAllEmployeesWorkInfo() {
        return (List<EmployeeWorkInfo>) employeeWorkInfoRepository.findAll();
    }

    public EmployeeWorkInfo createEmployeeWorkInfo(EmployeeWorkInfoCreateRequest createEmployeeWorkInfoRequest) {
        Optional<EmployeeWorkInfo> employeeId = getEmployeeWorkInfo(createEmployeeWorkInfoRequest.getId());
        Optional<EmployeeWorkInfo> employeeName = employeeWorkInfoRepository.searchWithNativeQuery(createEmployeeWorkInfoRequest.getName()).stream().findFirst();
        if (employeeId.isPresent()) {
            throw new BadRequestException("employeeWorkInfo.id.already.exists: " + getEmployeeWorkInfo(createEmployeeWorkInfoRequest.getId()).toString());
        } else if (employeeName.isPresent()) {
            throw new BadRequestException("employeeWorkInfo.name.already.exists: " + employeeWorkInfoRepository.searchWithNativeQuery(createEmployeeWorkInfoRequest.getName()).toString());
        } else {
            EmployeeWorkInfo employee = new EmployeeWorkInfo();

            employee.setId(createEmployeeWorkInfoRequest.getId());
            employee.setEmployeeName(createEmployeeWorkInfoRequest.getName());
            employee.setEmployeeFunction(createEmployeeWorkInfoRequest.getFunction());
            employee.setEmployeeDepart(createEmployeeWorkInfoRequest.getDepart());
            return employeeWorkInfoRepository.save(employee);
        }
    }

    public EmployeeWorkInfo updateEmployeeWorkInfo(EmployeeWorkInfoUpdateRequest updateEmployeeWorkInfoRequest) {
        Optional<EmployeeWorkInfo> employeeId = getEmployeeWorkInfo(updateEmployeeWorkInfoRequest.getId());
        if (!employeeId.isPresent()) {
            throw new BadRequestException("employeeWorkInfo.not.found");
        }
        EmployeeWorkInfo employee = new EmployeeWorkInfo();
        employee.setId(updateEmployeeWorkInfoRequest.getId());
        employee.setEmployeeName(updateEmployeeWorkInfoRequest.getName());
        employee.setEmployeeDepart(updateEmployeeWorkInfoRequest.getDepart());
        employee.setEmployeeFunction(updateEmployeeWorkInfoRequest.getFunction());
        return employeeWorkInfoRepository.save(employee);
    }


    public void deleteEmployeeWorkInfo(Integer employeeId) {
        Optional<EmployeeWorkInfo> employee = getEmployeeWorkInfo(employeeId);
        if (!employee.isPresent()) {
            throw new BadRequestException("employeeWorkInfo.not.found");
        }
        employeeWorkInfoRepository.deleteById(employeeId);
    }

    public List<EmployeeWorkInfo> getAllEmployeesWorkInfo(String employeeName, String employeeDepart) {
        if (!StringUtils.isEmpty(employeeName) && !StringUtils.isEmpty(employeeDepart)) {
            return employeeWorkInfoRepository.findByEmployeeNameAndEmployeeDepart(employeeName, employeeDepart);
        } else if (!StringUtils.isEmpty(employeeName)) {
            return employeeWorkInfoRepository.searchWithNativeQuery(employeeName);
        } else if (!StringUtils.isEmpty(employeeDepart)) {
            return employeeWorkInfoRepository.findByEmployeeDepart(employeeDepart);
        }
        return Collections.emptyList();
    }
}
