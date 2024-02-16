package tuto.sboot;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BonjourTest {
	@Autowired
	private BonjourService ecc;

	@Test
	void contextLoads() {
	}
	@Test
	public void testBonjourComplique() {
		String messageAttendu = "Bonjooouur ! J'arrive à faire du spring !)";
		String messageRecut = ecc.getMessage().message;
        assertEquals(messageAttendu, messageRecut);
	}
}
