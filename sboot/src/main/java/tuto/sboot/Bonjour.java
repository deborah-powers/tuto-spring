package tuto.sboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/* classe qui permet d'afficher un message dans la console */
@Component
public class Bonjour implements CommandLineRunner {

	@Autowired
	private BonjourService ecc;
    @Autowired
    private CustomProperty propriete;
	@Override
	public void run(String... args) throws Exception {
		System.out.println("HELLOOOOW !! "+ propriete.getMessage());
		BonjourModel message = ecc.getMessage();
		System.out.println(message);
	}
}
