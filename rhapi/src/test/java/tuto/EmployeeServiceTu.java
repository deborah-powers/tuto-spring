package tuto;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import tuto.EmployeeModel;
import tuto.EmployeeRepository;
import tuto.EmployeeService;

@SpringBootTest
public class EmployeeServiceTu {
	@Autowired
	private EmployeeService service;
    @MockBean
    private EmployeeRepository employeeRepository;

	@Test
	void contextLoads() {
	}
	@Test
	public void getEmployeesTest() {
		Iterable<EmployeeModel> resultat = service.getEmployees();
		System.out.println(resultat);
	}
}
