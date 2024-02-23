package tuto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.Data;

@Data
@Service
public class EmployeeService{
	@Autowired
	private EmployeeProxy proxy;

	public Iterable<EmployeeModel> getEmployees(){ return proxy.getEmployees(); }

	public EmployeeModel getEmployee(Long id){ return proxy.getEmployee(id); }

	public void deleteEmployee(Long id){ proxy.deleteEmployee(id); }

	public EmployeeModel saveEmployee(EmployeeModel employee){
		EmployeeModel savedEmployee;
		// Règle de gestion : Le nom de famille doit être mis en majuscule.
		if (employee.getLastName() != null)
			employee.setLastName(employee.getLastName().toUpperCase());
		if (employee.getId() == null)
			savedEmployee = proxy.createEmployee(employee);
		else
			savedEmployee = proxy.updateEmployee(employee);
		return savedEmployee;
	}
}
