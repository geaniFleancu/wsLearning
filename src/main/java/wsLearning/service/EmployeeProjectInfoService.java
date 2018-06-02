package wsLearning.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import wsLearning.exception.BadRequestException;
import wsLearning.model.EmployeeProjectInfo;
import wsLearning.model.Requests.EmployeeProjectInfoCreateRequest;
import wsLearning.model.Requests.EmployeeProjectInfoUpdateRequest;
import wsLearning.repository.EmployeeProjectInfoRepository;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EmployeeProjectInfoService {

    @Autowired
    private EmployeeProjectInfoRepository employeeProjectInfoRepository;

    public Optional<EmployeeProjectInfo> getEmployeeProjectInfo(Integer employeeId) {
        return employeeProjectInfoRepository.findById(employeeId);
    }

    public List<EmployeeProjectInfo> getAllEmployeesProjectInfo() {
        return (List<EmployeeProjectInfo>) employeeProjectInfoRepository.findAll();
    }

    public EmployeeProjectInfo createEmployeeProjectInfo(EmployeeProjectInfoCreateRequest createEmployeeProjectInfoRequest) {
        Optional<EmployeeProjectInfo> employeeId = getEmployeeProjectInfo(createEmployeeProjectInfoRequest.getId());
        Optional<EmployeeProjectInfo> employeeName = employeeProjectInfoRepository.searchWithNativeQuery(createEmployeeProjectInfoRequest.getName()).stream().findFirst();
        if (employeeId.isPresent()) {
            throw new BadRequestException("employeeProjectInfo.id.already.exists: " + getEmployeeProjectInfo(createEmployeeProjectInfoRequest.getId()).toString());
        } else if (employeeName.isPresent()) {
            throw new BadRequestException("employeeProjectInfo.name.already.exists: " + employeeProjectInfoRepository.searchWithNativeQuery(createEmployeeProjectInfoRequest.getName()).toString());
        } else {
            EmployeeProjectInfo employee = new EmployeeProjectInfo();

            employee.setId(createEmployeeProjectInfoRequest.getId());
            employee.setEmployeeName(createEmployeeProjectInfoRequest.getName());
            employee.setEmployeeProject(createEmployeeProjectInfoRequest.getProject());
            return employeeProjectInfoRepository.save(employee);
        }
    }

    public EmployeeProjectInfo updateEmployeeProjectInfo(EmployeeProjectInfoUpdateRequest updateEmployeeProjectInfoRequest) {
        Optional<EmployeeProjectInfo> employeeId = getEmployeeProjectInfo(updateEmployeeProjectInfoRequest.getId());
        if (!employeeId.isPresent()) {
            throw new BadRequestException("employeeProjectInfo.not.found");
        }
        EmployeeProjectInfo employee = new EmployeeProjectInfo();
        employee.setId(updateEmployeeProjectInfoRequest.getId());
        employee.setEmployeeName(updateEmployeeProjectInfoRequest.getName());
        employee.setEmployeeProject(updateEmployeeProjectInfoRequest.getProject());
        return employeeProjectInfoRepository.save(employee);
    }


    public void deleteEmployeeProjectInfo(Integer employeeId) {
        Optional<EmployeeProjectInfo> employee = getEmployeeProjectInfo(employeeId);
        if (!employee.isPresent()) {
            throw new BadRequestException("employeeProjectInfo.not.found");
        }
        employeeProjectInfoRepository.deleteById(employeeId);
    }

    public List<EmployeeProjectInfo> getAllEmployeesProjectInfo(String employeeName, String employeeProject) {
        if (!StringUtils.isEmpty(employeeName) && !StringUtils.isEmpty(employeeProject)) {
            return employeeProjectInfoRepository.findByEmployeeNameAndEmployeeProject(employeeName, employeeProject);
        } else if (!StringUtils.isEmpty(employeeName)) {
            return employeeProjectInfoRepository.searchWithNativeQuery(employeeName);
        } else if (!StringUtils.isEmpty(employeeProject)) {
            return employeeProjectInfoRepository.findByEmployeeProject(employeeProject);
        }
        return Collections.emptyList();
    }
}
