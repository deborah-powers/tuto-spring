package tuto;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository	// cette classe est un bean communiquant avec la base. on peut le remplacer par @Component
/*
 * CrudRepository vient de spring. elle travaille à partir d'une entité comme Employee
 * spring étends de lui-même cette interface. les fonctions d'intérêt sont déclarées dans CrudRepository
 */
public interface EmployeeRepository extends CrudRepository<EmployeeModel, Long> {

}
