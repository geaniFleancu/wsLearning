package wsLearning.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import wsLearning.model.EmployeeWorkInfo;
import wsLearning.model.Requests.EmployeeWorkInfoCreateRequest;
import wsLearning.model.Requests.ErrorResponseEntity;
import wsLearning.service.EmployeeWorkInfoService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

@RestController    // This means that this class is a Controller
@RequestMapping(path = "/employeeWorkInfo") // This means URL's start with /employee (after Application path)
public class EmployeeWorkInfoConroller {

    @Autowired
    private EmployeeWorkInfoService employeeWorkInfoService;

    @PostMapping(value = "/createEmployeeWorkInfo")
    public EmployeeWorkInfo createEmployeeWorkInfo(@RequestBody EmployeeWorkInfoCreateRequest createEmployeeWorkInfoRequest) {
        return employeeWorkInfoService.createEmployeeWorkInfo(createEmployeeWorkInfoRequest);
    }

    @GetMapping(value = "/getAllEmployeesWorkInfo")
    public List<EmployeeWorkInfo> getAllEmployeeWorkInfo(@RequestParam(name = "employeeName", required = false) String employeeName,
                                                         @RequestParam(name = "departName", required = false) String departName) {
        if (StringUtils.isEmpty(employeeName) && StringUtils.isEmpty(departName)) {
            return employeeWorkInfoService.getAllEmployeesWorkInfo();
        } else if (StringUtils.isEmpty(employeeName) && !StringUtils.isEmpty(departName)) {
            return employeeWorkInfoService.getAllEmployeesWorkInfo();
        } else {
            return employeeWorkInfoService.getAllEmployeesWorkInfo(employeeName, departName);
        }

    }

    @GetMapping(value = "/getEmployeeWorkInfo/{employeeId}")
    public Object employee(@PathVariable(value = "employeeId") Integer employeeId,
                           HttpServletRequest request,
                           HttpServletResponse response) {
        Optional<EmployeeWorkInfo> employeeOpt = employeeWorkInfoService.getEmployeeWorkInfo(employeeId);
        if (employeeOpt.isPresent()) {
            return employeeOpt.get();
        } else {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            return new ErrorResponseEntity("employee.not.found", HttpStatus.BAD_REQUEST.value());
        }
    }


    @DeleteMapping(value = "/deleteEmployeeWorkInfo/{employeeId}")
    public void deleteEmployeeWorkInfo(@PathVariable(name = "employeeId") Integer employeeId) {
        employeeWorkInfoService.deleteEmployeeWorkInfo(employeeId);
    }
}
