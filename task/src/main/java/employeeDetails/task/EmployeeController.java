package employeeDetails.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    @Operation(summary = "Add a new employee", description = "This endpoint allows you to add a new employee into the system.")
    @ApiResponse(responseCode = "201", description = "Employee created successfully")
    @ApiResponse(responseCode = "400", description = "Bad request due to validation errors")
    public ResponseEntity<EmployeeDetails> addEmployee(@RequestBody EmployeeDto employeeDto) {
        EmployeeDetails employee = employeeService.saveEmployee(employeeDto);
        return new ResponseEntity<>(employee, HttpStatus.CREATED);
    }

    @GetMapping("/{employeeId}/tax-deductions")
    @Operation(summary = "Get tax deductions for an employee", description = "This endpoint calculates the tax deductions for the employee based on their salary.")
    @ApiResponse(responseCode = "200", description = "Tax deductions calculated successfully")
    @ApiResponse(responseCode = "404", description = "Employee not found")
    public ResponseEntity<TaxDeductionsResponse> getEmployeeTaxDeductions(@PathVariable int employeeId) {
        EmployeeDetails employee = employeeService.getEmployeeById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        double taxDeductions = employeeService.calculateTax(employee);

        TaxDeductionsResponse response = new TaxDeductionsResponse();
        response.setEmployeeId(employee.getEmployeeId());
        response.setFirstName(employee.getFirstName());
        response.setLastName(employee.getLastName());
        response.setTaxDeductions(taxDeductions);

        return ResponseEntity.ok(response);
    }
}
