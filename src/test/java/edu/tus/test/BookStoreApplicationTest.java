package edu.tus.test;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import edu.tus.wizworld.BookstoreApplication;

@SpringBootTest(classes = BookstoreApplication.class)
@ActiveProfiles("test")

public class BookStoreApplicationTest {
	
	@Test
	public void contextLoads() {
		BookstoreApplication.main(new String[]{});
	}	
}
