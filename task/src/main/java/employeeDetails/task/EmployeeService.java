package employeeDetails.task;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    // Method to save employee details
    public EmployeeDetails saveEmployee(EmployeeDto employeeDto) {
    	EmployeeDetails employee = new EmployeeDetails();
        employee.setEmployeeId(generateEmployeeId());
        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employee.setEmail(employeeDto.getEmail());
        employee.setPhoneNumbers(employeeDto.getPhoneNumbers());
        employee.setDoj(employeeDto.getDoj());
        employee.setSalary(employeeDto.getSalary());

        return employeeRepository.save(employee);
    }

    public String generateEmployeeId() {
        Long count = employeeRepository.count();  // Get the total number of employees
        int nextId = count.intValue() + 1;  // Increment the count by 1
        return "E" + String.format("%03d", nextId);  // Format as E001, E002, etc.
    }

    // Method to get employee by ID
    public Optional<EmployeeDetails> getEmployeeById(int employeeId) {
        return employeeRepository.findById(employeeId);
    }

    // Method to calculate the tax deductions for an employee
    public double calculateTax(EmployeeDetails employee) {
        LocalDate doj = LocalDate.parse(employee.getDoj());
        LocalDate currentDate = LocalDate.now();
        long monthsWorked = ChronoUnit.MONTHS.between(doj, currentDate);

        double monthlySalary = employee.getSalary();
        double yearlySalary = monthlySalary * monthsWorked;

        // Adjust salary for partial year if needed
        double taxAmount = 0;

        if (yearlySalary <= 250000) {
            taxAmount = 0;
        } else if (yearlySalary <= 500000) {
            taxAmount = (yearlySalary - 250000) * 0.05;
        } else if (yearlySalary <= 1000000) {
            taxAmount = (yearlySalary - 500000) * 0.10 + 250000 * 0.05;
        } else {
            taxAmount = (yearlySalary - 1000000) * 0.20 + 500000 * 0.10 + 250000 * 0.05;
        }

        double cessAmount = 0;
        if (yearlySalary > 2500000) {
            cessAmount = (yearlySalary - 2500000) * 0.02;
        }

        return taxAmount + cessAmount;
    }
}