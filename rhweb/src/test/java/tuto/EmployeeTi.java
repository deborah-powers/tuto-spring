package tuto;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

//charger le nécessaire pour tester
@SpringBootTest // charger spring
@AutoConfigureMockMvc // échanger avec le controlleur
public class EmployeeTi{
	// échanger avec le controlleur
	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testGetEmployees() throws Exception{
		mockMvc.perform(get("/")).andDo(print()) // afficher le retour dans la console
				.andExpect(status().isOk()).andExpect(view().name("home"))
				.andExpect(MockMvcResultMatchers.content().string(containsString("Laurent")));
	}
	
	@Test
	public void testCreateEmployee() throws Exception{
		// creer l'employé
		EmployeeModel employee = new EmployeeModel();
		employee.setFirstName("david");
		employee.setLastName("carroussi");
		employee.setMail("david.carroussi@com");
		employee.setPassword("cocolasticot");
		String json = new ObjectMapper().writeValueAsString(employee);
		// envoyer la requête
		mockMvc.perform(post("/saveEmployee").flashAttr("employee", employee)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
	}
	public void testCreateEmployee_methodeInadaptee() throws Exception{
		// creer l'employé
		EmployeeModel employee = new EmployeeModel();
		employee.setFirstName("david");
		employee.setLastName("carroussi");
		employee.setMail("david.carroussi@com");
		employee.setPassword("cocolasticot");
		String json = new ObjectMapper().writeValueAsString(employee);
		// envoyer la requête
		mockMvc.perform(post("/saveEmployee")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json)
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
	}

	@Test
	public void testDeleteEmployee() throws Exception{
		mockMvc.perform(delete("/deleteEmployee/2")).andExpect(status().is(405));
	}
}
