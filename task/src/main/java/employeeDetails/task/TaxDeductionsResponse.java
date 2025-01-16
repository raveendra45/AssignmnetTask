package employeeDetails.task;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaxDeductionsResponse {
	  private String employeeId;
	    private String firstName;
	    private String lastName;
	    private double taxDeductions;
}
