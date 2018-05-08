package wsLearning.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import wsLearning.model.EmployeeInfo;
import wsLearning.model.Requests.EmployeeInfoCreateRequest;
import wsLearning.model.Requests.ErrorResponseEntity;
import wsLearning.service.EmployeeService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

@RestController    // This means that this class is a Controller
@RequestMapping(path = "/employee") // This means URL's start with /employee (after Application path)
public class EmployeeInfoController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping(value = "/createEmployee")
    public EmployeeInfo createEmployee(@RequestBody EmployeeInfoCreateRequest createEmployeeRequest) {
        return employeeService.createEmployee(createEmployeeRequest);
    }

    @GetMapping(value = "/getAllEmployees")
    public List<EmployeeInfo> getAllEmployee(@RequestParam(name = "employeeName", required = false) String employeeName,
                                             @RequestParam(name = "employeeEmail", required = false) String employeeEmail) {
        if (StringUtils.isEmpty(employeeName) && StringUtils.isEmpty(employeeEmail)) {
            return employeeService.getAllEmployees();
        } else {
            return employeeService.getAllEmployees(employeeName, employeeEmail);
        }

    }

    @GetMapping(value = "/getEmployee/{employeeId}")
    public Object greeting(@PathVariable(value = "employeeId") Long employeeId,
                           HttpServletRequest request,
                           HttpServletResponse response) {
        Optional<EmployeeInfo> employeeOpt = employeeService.getEmployee(employeeId);
        if (employeeOpt.isPresent()) {
            return employeeOpt.get();
        } else {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            return new ErrorResponseEntity("employee.not.found", HttpStatus.BAD_REQUEST.value());
        }
    }


    @DeleteMapping(value = "/deleteEmployee/{employeeId}")
    public void deleteEmployee(@PathVariable(name = "employeeId") Long employeeId) {
        employeeService.deleteEmployee(employeeId);
    }
}
