package wsLearning.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import wsLearning.model.EmployeeProjectInfo;
import wsLearning.model.Requests.EmployeeProjectInfoCreateRequest;
import wsLearning.model.Requests.EmployeeProjectInfoUpdateRequest;
import wsLearning.model.Requests.ErrorResponseEntity;
import wsLearning.service.EmployeeProjectInfoService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

@RestController    // This means that this class is a Controller
@RequestMapping(path = "/employeeProjectInfo") // This means URL's start with /employee (after Application path)
public class EmployeeProjectInfoConroller {

    @Autowired
    private EmployeeProjectInfoService employeeProjectInfoService;

    @PostMapping(value = "/createEmployeeProjectInfo")
    public EmployeeProjectInfo createEmployeeProjectInfo(@RequestBody EmployeeProjectInfoCreateRequest createEmployeeProjectInfoRequest) {
        return employeeProjectInfoService.createEmployeeProjectInfo(createEmployeeProjectInfoRequest);
    }

    @GetMapping(value = "/getAllEmployeesProjectInfo")
    public List<EmployeeProjectInfo> getAllEmployeeProjectInfo(@RequestParam(name = "employeeName", required = false) String employeeName,
                                                               @RequestParam(name = "projectName", required = false) String projectName) {
        if (StringUtils.isEmpty(employeeName) && StringUtils.isEmpty(projectName)) {
            return employeeProjectInfoService.getAllEmployeesProjectInfo();
        } else if (StringUtils.isEmpty(employeeName) && !StringUtils.isEmpty(projectName)) {
            return employeeProjectInfoService.getAllEmployeesProjectInfo();
        } else {
            return employeeProjectInfoService.getAllEmployeesProjectInfo(employeeName, projectName);
        }

    }

    @GetMapping(value = "/getEmployeeProjectInfo/{employeeId}")
    public Object employee(@PathVariable(value = "employeeId") Integer employeeId,
                           HttpServletRequest request,
                           HttpServletResponse response) {
        Optional<EmployeeProjectInfo> employeeOpt = employeeProjectInfoService.getEmployeeProjectInfo(employeeId);
        if (employeeOpt.isPresent()) {
            return employeeOpt.get();
        } else {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            return new ErrorResponseEntity("employee.not.found", HttpStatus.BAD_REQUEST.value());
        }
    }


    @DeleteMapping(value = "/deleteEmployeeProjectInfo/{employeeId}")
    public void deleteEmployeeProjectInfo(@PathVariable(name = "employeeId") Integer employeeId) {
        employeeProjectInfoService.deleteEmployeeProjectInfo(employeeId);
    }

    @PutMapping(value = "/updateEmployeeProjectInfo")
    public EmployeeProjectInfo updateEmployeeProjectInfo(@RequestBody EmployeeProjectInfoUpdateRequest updateEmployeeProjectInfoRequest) {
        return employeeProjectInfoService.updateEmployeeProjectInfo(updateEmployeeProjectInfoRequest);
    }
}
