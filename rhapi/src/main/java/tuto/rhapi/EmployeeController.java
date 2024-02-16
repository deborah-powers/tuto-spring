package tuto.rhapi;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * contient les endpoints de l'api. l'appli appelant cette api doit utiliser les fonctions précisées ici.
 */
/* annotation de spring indiquant que la classe est un bean.
elle précise que nous utilisons la méthode rest, qui renvoi ses résultats au format json.
*/
@RestController
public class EmployeeController {
	/* annotation de spring-boot.
	initialise le bean automatiquement,
	grace à Application.@SpringBootApplication ou @ComponentScan("mon.package")
	*/
	@Autowired
    private EmployeeService employeeService;

    /**
    * Read - Get all employees
    * @return - An Iterable object of Employee full filled
    * @GetMapping transforme cette méthode en endpoint en précisant son url
    * si jeu veux accéder à la liste des employés via mon navigateur, je dois utiliser l'url
    * http://localhost:1407/employees
    */
    @GetMapping("/employees")
    public Iterable<EmployeeModel> getEmployees(){
        return employeeService.getEmployees();
    }
    @GetMapping("/employee/{id}")
	public EmployeeModel getEmployee(@PathVariable("id") final Long id) {
		Optional<EmployeeModel> employee = employeeService.getEmployee(id);
		if(employee.isPresent()) {
			return employee.get();
		} else {
			return null;
		}
	}
    @PostMapping("/employee")
    public Iterable<EmployeeModel> createEmployee(){
        return employeeService.getEmployees();
    }
}
