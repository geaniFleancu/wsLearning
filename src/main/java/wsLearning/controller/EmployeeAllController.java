package wsLearning.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import wsLearning.exception.BadRequestException;
import wsLearning.model.Employee;
import wsLearning.model.Requests.*;
import wsLearning.service.EmployeeInfoService;
import wsLearning.service.EmployeeProjectInfoService;
import wsLearning.service.EmployeeService;
import wsLearning.service.EmployeeWorkInfoService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

@RestController    // This means that this class is a Controller
@RequestMapping(path = "/employee") // This means URL's start with /employee (after Application path)
public class EmployeeAllController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private EmployeeInfoService employeeInfoService;
    @Autowired
    private EmployeeWorkInfoService employeeWorkInfoService;
    @Autowired
    private EmployeeProjectInfoService employeeProjectInfoService;

    @PostMapping(value = "/createEmployee")
    public Employee createEmployee(@RequestBody EmployeeAllCreateRequest createEmployeeAllRequest) {

        if (createEmployeeAllRequest.getId() == null) {
            throw new BadRequestException("employee.id.is.mandatory.for.all.create!");
        }

        EmployeeInfoCreateRequest createEmployeeInfoRequest = new EmployeeInfoCreateRequest();
        createEmployeeInfoRequest.setId(createEmployeeAllRequest.getId());
        createEmployeeInfoRequest.setName(createEmployeeAllRequest.getName());
        createEmployeeInfoRequest.setAge(createEmployeeAllRequest.getAge());
        createEmployeeInfoRequest.setEmail(createEmployeeAllRequest.getEmail());
        employeeInfoService.createEmployee(createEmployeeInfoRequest);

        EmployeeWorkInfoCreateRequest createEmployeeWorkInfoRequest = new EmployeeWorkInfoCreateRequest();
        createEmployeeWorkInfoRequest.setId(createEmployeeAllRequest.getId());
        createEmployeeWorkInfoRequest.setName(createEmployeeAllRequest.getName());
        createEmployeeWorkInfoRequest.setDepart(createEmployeeAllRequest.getDepart());
        createEmployeeWorkInfoRequest.setFunction(createEmployeeAllRequest.getFunction());
        employeeWorkInfoService.createEmployeeWorkInfo(createEmployeeWorkInfoRequest);

        EmployeeProjectInfoCreateRequest createEmployeeProjectInfoRequest = new EmployeeProjectInfoCreateRequest();
        createEmployeeProjectInfoRequest.setId(createEmployeeAllRequest.getId());
        createEmployeeProjectInfoRequest.setName(createEmployeeAllRequest.getName());
        createEmployeeProjectInfoRequest.setProject(createEmployeeAllRequest.getProject());
        employeeProjectInfoService.createEmployeeProjectInfo(createEmployeeProjectInfoRequest);

        return employeeService.createEmployee(createEmployeeAllRequest);
    }

    @GetMapping(value = "/getAllEmployees")
    public List<Employee> getAllEmployee(@RequestParam(name = "employeeName", required = false) String employeeName,
                                         @RequestParam(name = "employeeEmail", required = false) String employeeEmail) {
        if (StringUtils.isEmpty(employeeName) && StringUtils.isEmpty(employeeEmail)) {
            return employeeService.getAllEmployees();
        } else {
            return employeeService.getAllEmployees(employeeName, employeeEmail);
        }

    }

    @GetMapping(value = "/getEmployee/{employeeId}")
    public Object employee(@PathVariable(value = "employeeId") Integer employeeId,
                           HttpServletRequest request,
                           HttpServletResponse response) {
        Optional<Employee> employeeOpt = employeeService.getEmployee(employeeId);
        if (employeeOpt.isPresent()) {
            return employeeOpt.get();
        } else {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            return new ErrorResponseEntity("employee.not.found", HttpStatus.BAD_REQUEST.value());
        }
    }


    @DeleteMapping(value = "/deleteEmployee/{employeeId}")
    public void deleteEmployee(@PathVariable(name = "employeeId") Integer employeeId) {
        employeeInfoService.deleteEmployee(employeeId);
        employeeWorkInfoService.deleteEmployeeWorkInfo(employeeId);
        employeeProjectInfoService.deleteEmployeeProjectInfo(employeeId);
    }

    @PutMapping(value = "/updateEmployee")
    public Employee updateEmployee(@RequestBody EmployeeAllUpdateRequest updateEmployeeRequest) {

        EmployeeInfoUpdateRequest updateEmployeeInfoRequest = new EmployeeInfoUpdateRequest();
        updateEmployeeInfoRequest.setId(updateEmployeeRequest.getId());
        updateEmployeeInfoRequest.setName(updateEmployeeRequest.getName());
        updateEmployeeInfoRequest.setAge(updateEmployeeRequest.getAge());
        updateEmployeeInfoRequest.setEmail(updateEmployeeRequest.getEmail());
        employeeInfoService.updateEmployee(updateEmployeeInfoRequest);

        EmployeeWorkInfoUpdateRequest updateEmployeeWorkInfoRequest = new EmployeeWorkInfoUpdateRequest();
        updateEmployeeWorkInfoRequest.setId(updateEmployeeRequest.getId());
        updateEmployeeWorkInfoRequest.setName(updateEmployeeRequest.getName());
        updateEmployeeWorkInfoRequest.setDepart(updateEmployeeRequest.getDepart());
        updateEmployeeWorkInfoRequest.setFunction(updateEmployeeRequest.getFunction());
        employeeWorkInfoService.updateEmployeeWorkInfo(updateEmployeeWorkInfoRequest);

        EmployeeProjectInfoUpdateRequest updateEmployeeProjectInfoRequest = new EmployeeProjectInfoUpdateRequest();
        updateEmployeeProjectInfoRequest.setId(updateEmployeeRequest.getId());
        updateEmployeeProjectInfoRequest.setName(updateEmployeeRequest.getName());
        updateEmployeeProjectInfoRequest.setProject(updateEmployeeRequest.getProject());
        employeeProjectInfoService.updateEmployeeProjectInfo(updateEmployeeProjectInfoRequest);

        return employeeService.updateEmployee(updateEmployeeRequest);
    }
}
