package tuto.sboot;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

/* créer une propriété maison, associée à application.properties / custom.property
 * 
 */
@Configuration	// ma classe est un bean de configuration
@ConfigurationProperties(prefix = "custom.property")	// associer la classe à la propriété. sboot lis de lui-même tous les fichiers .properties
@Data
public class CustomProperty {
	private String message;
}
