package tuto;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import lombok.extern.slf4j.Slf4j;

@Slf4j
//charger le nécessaire pour tester
@SpringBootTest
public class EmployeeProxyTu {
	@Autowired
	EmployeeProxy proxy;

    @Test
    public void testGetEmployees() throws Exception {
    	Iterable<EmployeeModel> empoyees = proxy.getEmployees();
    }
    @Test
    public void testGetEmployee() throws Exception {
    	Long id=2L;
    	EmployeeModel empoyee = proxy.getEmployee(id);
        assertEquals("Sophie", empoyee.getFirstName());
        assertEquals("FONCEK", empoyee.getLastName());
    }
}
