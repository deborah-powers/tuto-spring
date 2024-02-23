package tuto;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.Data;

@Data	// annotation de lombock pour replacer les getters et setters. cette classe est un bean.
@Service	// cette classe est un bean éffectuant des traitements métier. on peut le remplacer par @Component
public class EmployeeService {
	/* annotation de spring-boot.
	initialise le bean automatiquement,
	grace à Application.@SpringBootApplication ou @ComponentScan("mon.package")
	*/
	@Autowired
    private EmployeeRepository employeeRepository;

    public Optional<EmployeeModel> getEmployee(final Long id) {
        return employeeRepository.findById(id);
    }

    public Iterable<EmployeeModel> getEmployees() {
        return employeeRepository.findAll();
    }

    public void deleteEmployee(final Long id) {
        employeeRepository.deleteById(id);
    }

    public EmployeeModel saveEmployee(EmployeeModel employee) {
    	EmployeeModel savedEmployee = employeeRepository.save(employee);
        return savedEmployee;
    }
}
