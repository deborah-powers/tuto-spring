package tuto.sboot;

import org.springframework.stereotype.Component;

@Component
public class BonjourService {
	public BonjourModel getMessage() {
		return new BonjourModel();
	}
}
