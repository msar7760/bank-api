package com.yasir.bank;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class BankApiTests {
	@Value("${spring.profiles.active}")
	private String activeProfile;

	@Test
	void contextLoads() {
		assertNotNull(activeProfile);
		assertNotEquals("prod",activeProfile);
	}
}
