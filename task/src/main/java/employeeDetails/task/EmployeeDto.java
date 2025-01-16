package employeeDetails.task;

import java.util.List;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeDto {

    @NotEmpty(message = "Employee ID cannot be empty")
    private String employeeId;

    @NotEmpty(message = "First name cannot be empty")
    private String firstName;

    @NotEmpty(message = "Last name cannot be empty")
    private String lastName;

    @Email(message = "Email should be valid")
    private String email;

    @NotEmpty(message = "Phone numbers cannot be empty")
    private List<@Pattern(regexp = "^\\d{10}$", message = "Phone number should be 10 digits") String> phoneNumbers;

    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "DOJ should be in YYYY-MM-DD format")
    private String doj;

    @Positive(message = "Salary should be a positive number")
    private double salary;

    // Getters and Setters
}
