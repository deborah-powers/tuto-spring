package tuto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		// les exit assurent que l'on quitte l'application à la fin du batch
		SpringApplication.run(Application.class, args);
	}

}
