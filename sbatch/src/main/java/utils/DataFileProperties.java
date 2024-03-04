package utils;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration	// ma classe est un bean de configuration
@ConfigurationProperties(prefix = "data.file")	// associer la classe à la propriété. sboot lis de lui-même tous les fichiers .properties
public class DataFileProperties{
	private String root;
	private String path;
	private String src;
	private String dst;
	public String getSrc() {
		return src;
	}
	public String getDst() {
		return dst;
	}
	public String getSrcPath() {
		return root + path + src;
	}
	public String getDstPath() {
		return root + path + dst;
	}
}