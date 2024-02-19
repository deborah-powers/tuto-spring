package tuto;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

/* créer une propriété maison, associée à application.properties / api
 * 
 */
@Configuration	// ma classe est un bean de configuration
@ConfigurationProperties(prefix = "api")	// associer la classe à la propriété. sboot lis de lui-même tous les fichiers .properties
@Data
public class ApiProps {
	private String url;
	private String urlEmployee;
	private String urlEmployees;
}
