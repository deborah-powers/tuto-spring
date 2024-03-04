package tuto;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

/*
 * contient les endpoints de l'api. l'appli appelant cette api doit utiliser les fonctions précisées ici.
 */
/* annotation de spring indiquant que la classe est un bean.
elle précise que nous utilisons la méthode rest, qui renvoi ses résultats au format json.
*/
@Slf4j
@RestController
public class EmployeeController {
	/*
	 * annotation de spring-boot. initialise le bean automatiquement, grace à
	 * Application.@SpringBootApplication ou @ComponentScan("mon.package")
	 */
	@Autowired
	private EmployeeService employeeService;

	/**
	 * Read - Get all employees
	 * 
	 * @return - An Iterable object of Employee full filled
	 * @GetMapping transforme cette méthode en endpoint en précisant son url si jeu
	 *             veux accéder à la liste des employés via mon navigateur, je dois
	 *             utiliser l'url http://localhost:1407/employees
	 */
	@GetMapping("/employees")
	public Iterable<EmployeeModel> getEmployees() {
		return employeeService.getEmployees();
	}
	@GetMapping("/employee/{id}")
	public EmployeeModel getEmployee(@PathVariable("id") final Long id) {
		Optional<EmployeeModel> employee = employeeService.getEmployee(id);
		if (employee.isPresent()) {
			return employee.get();
		} else {
			return null;
		}
	}

	@DeleteMapping("/employee/{id}")
	public void deleteEmployee(@PathVariable("id") final Long id) {
		employeeService.deleteEmployee(id);
	}

	@PostMapping("/employee")
	public EmployeeModel createEmployee(@RequestBody EmployeeModel employee) {
		return employeeService.saveEmployee(employee);
	}

	@PutMapping("/employee/{id}")
	public EmployeeModel updateEmployee(@PathVariable("id") final Long id, @RequestBody EmployeeModel employee) {
		Optional<EmployeeModel> employeeOptional = employeeService.getEmployee(id);
		if (employeeOptional.isPresent()) {
			EmployeeModel currentEmployee = employeeOptional.get();
			String firstName = employee.getFirstName();
			if (firstName != null) {
				currentEmployee.setFirstName(firstName);
			}
			String lastName = employee.getLastName();
			if (lastName != null) {
				currentEmployee.setLastName(lastName);
				;
			}
			String mail = employee.getMail();
			if (mail != null) {
				currentEmployee.setMail(mail);
			}
			String password = employee.getPassword();
			if (password != null) {
				currentEmployee.setPassword(password);
			}
			employeeService.saveEmployee(currentEmployee);
			return currentEmployee;
		} else {
			return null;
		}
	}
}
