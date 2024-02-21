package tuto;

import javax.swing.text.html.HTML;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller // cette classe est un bean communiquant avec l'écran. on peut le remplacer par @Component
public class EmployeeController{
	@Autowired
	private EmployeeService service;

	@GetMapping("/deleteEmployee/{id}")
	public ModelAndView deleteEmployee(@PathVariable("id") final Long id){
		// traiter une donnée transmise par url
		service.deleteEmployee(id);
		// ModelAndView permet de rediriger, renvoyer des donnée (model) et une page (view) en même temps
		return new ModelAndView("redirect:/");
	}

	@PostMapping("/saveEmployee")
	public ModelAndView saveEmployee(@ModelAttribute EmployeeModel employee){
		// traiter une donnée transmise par formulaire
		/* @ModelAttribute permet de récupérer les données envoyées via un formulaire.
		 * ce formulaire correspond à un objet nommé employee. */
		service.saveEmployee(employee);
		return new ModelAndView("redirect:/");
	}

	@GetMapping("/")
	public String home(Model model){
		Iterable<EmployeeModel> listEmployee = service.getEmployees();
		/* Model stoque les données transmise à la vue html.
		 * thymeleaf, le moteur de template, permet de les récupérer.
		 * il correspond à home.html / "${employees}". */
		model.addAttribute("employees", listEmployee);
		model.addAttribute("employee", new EmployeeModel());
		return "home";
	}

	@GetMapping("/accueil")
	public String accueil(){
		// la string "accueil-simple" correspond au nom de templates/accueil-simple.html
		return "accueil-simple";
	}
}
