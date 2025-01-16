package employeeDetails.task;

import java.util.List;


import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
@Entity
@Table
@Getter
@Setter
public class EmployeeDetails {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
	private String employeeId;
    private String firstName;
    private String lastName;
    private String email;
    @ElementCollection
    @CollectionTable(
        name = "employee_phone_numbers",
        joinColumns = @JoinColumn(name = "employee_id")
    )
    @Column(name = "phone_number")
    private List<String> phoneNumbers;
    
    private String doj; 
    private double salary;
}
