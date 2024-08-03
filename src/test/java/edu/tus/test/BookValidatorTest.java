package edu.tus.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import edu.tus.wizworld.dao.BookRepository;
import edu.tus.wizworld.dto.Book;
import edu.tus.wizworld.dto.Rating;
import edu.tus.wizworld.errors.BookValidator;
import edu.tus.wizworld.errors.ErrorMessages;
import edu.tus.wizworld.exceptions.BookValidationException;

public class BookValidatorTest {
	@Mock
	BookRepository bookRepo;

	@InjectMocks
	BookValidator bookValidator;

	@BeforeEach
	void setUp() throws IOException {
		MockitoAnnotations.openMocks(this);
		mockBook = new Book(100L, "Book-1", "Nagesh", "Rao", 10.25, 15.25, "Image1.png", "Series", Rating.CLASSIC);
	}

	private Book mockBook;
	
	@Test
	public void checkNoIssuesTest() throws BookValidationException {
		bookValidator.validateBook(mockBook);
	}

	@Test
	public void checkEmptyFieldForTitleTest() {
		mockBook = new Book(100L, "", "Macharla", "mnrao", 10.25, 15.25, "Image2.png", "Comics", Rating.CLASSIC);
		Throwable ex = assertThrows(BookValidationException.class, () ->
			bookValidator.validateBook(mockBook)
		);
		assertEquals(ErrorMessages.EMPTY_FIELDS.getMsg(), ex.getMessage());
	}
	
	@Test
	public void checkEmptyFieldForSeriesTest() {
		mockBook = new Book(100L, "Title", "Author", "IllustratedBy", 10.25, 15.25, "Image", "", Rating.CLASSIC);
		Throwable ex = assertThrows(BookValidationException.class, () ->
			bookValidator.validateBook(mockBook)
		);
		assertEquals(ErrorMessages.EMPTY_FIELDS.getMsg(), ex.getMessage());
	}

	@Test
	public void checkIfTitleAlreadyExistsTest() {
		when(bookRepo.findByTitle(mockBook.getTitle())).thenReturn(mockBook);
		Throwable ex = assertThrows(BookValidationException.class, () ->
			bookValidator.validateBook(mockBook)
		);
		assertEquals(ErrorMessages.ALREADY_EXISTS.getMsg(), ex.getMessage());
	}
}
