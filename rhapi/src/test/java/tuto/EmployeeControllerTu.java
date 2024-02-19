package tuto;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import tuto.EmployeeController;
import tuto.EmployeeService;


// charger le nécessaire pour tester les controllers
@WebMvcTest(controllers = EmployeeController.class)
public class EmployeeControllerTu {
	// imite l'appli appelant mon api
	@Autowired
    private MockMvc mockMvc;
    @MockBean
    private EmployeeService employeeService;
    @Test
    public void testGetEmployees() throws Exception {
        mockMvc.perform(get("/employees")).andExpect(status().isOk());
    }
    @Test
    public void testGetEmployee() throws Exception {
        mockMvc.perform(get("/employee/2")).andExpect(status().isOk());
    }
}
