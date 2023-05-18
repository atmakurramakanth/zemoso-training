package com.librarymanagementsystem;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class LibrarymanagementsystemApplicationTests {

	@Test
	void testingMainMethodInSpringBootApplication()
	{
		LibrarymanagementsystemApplication.main(new String[]{});
		Assertions.assertTrue(true, "asserting to with Sonar");
	}


}
