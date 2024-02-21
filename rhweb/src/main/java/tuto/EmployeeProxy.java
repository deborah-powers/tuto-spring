package tuto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

// source de données, appelle l'api
@Slf4j
@Component
public class EmployeeProxy{
	@Autowired
	private ApiProps apiProp;
	private RestTemplate restTemplate;

	public Iterable<EmployeeModel> getEmployees(){
		String url = apiProp.getUrlEmployees();
		restTemplate = new RestTemplate();
		ResponseEntity<Iterable<EmployeeModel>> response = restTemplate.exchange(url, HttpMethod.GET, null,
				new ParameterizedTypeReference<Iterable<EmployeeModel>>(){});
		log.debug("status: " + response.getStatusCode().toString());
		return response.getBody();
	}

	public EmployeeModel getEmployee(Long id){
		String url = apiProp.getUrlEmployee();
		url = url + id;
		restTemplate = new RestTemplate();
		ResponseEntity<EmployeeModel> response = restTemplate.exchange(url, HttpMethod.GET, null, EmployeeModel.class);
		log.debug("status: " + response.getStatusCode().toString());
		return response.getBody();
	}

	public void deleteEmployee(Long id){
		String url = apiProp.getUrlEmployee();
		url = url + id;
		restTemplate = new RestTemplate();
		ResponseEntity<Void> response = restTemplate.exchange(url, HttpMethod.DELETE, null, Void.class);
		log.debug("status: " + response.getStatusCode().toString());
		}

	public EmployeeModel createEmployee(EmployeeModel employee){
		String url = apiProp.getUrlEmployee();
		restTemplate = new RestTemplate();
		HttpEntity<EmployeeModel> request = new HttpEntity<EmployeeModel>(employee);
		ResponseEntity<EmployeeModel> response = restTemplate.exchange(url, HttpMethod.POST, request, EmployeeModel.class);
		log.debug("status: " + response.getStatusCode().toString());
		return response.getBody();
	}

	public EmployeeModel updateEmployee(EmployeeModel employee){
		String url = apiProp.getUrlEmployee();
		// si param id: url = url + id;
		url = url + employee.getId();
		restTemplate = new RestTemplate();
		HttpEntity<EmployeeModel> request = new HttpEntity<EmployeeModel>(employee);
		ResponseEntity<EmployeeModel> response = restTemplate.exchange(url, HttpMethod.PUT, request, EmployeeModel.class);
		log.debug("status: " + response.getStatusCode().toString());
		return response.getBody();
	}
}
