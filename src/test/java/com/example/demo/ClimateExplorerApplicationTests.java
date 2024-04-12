package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
@SpringBootTest
class ClimateExplorerApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	public void testMainMethod() {
		assertDoesNotThrow(() -> ClimateExplorerApplication.main(new String[]{}));
	}

}
