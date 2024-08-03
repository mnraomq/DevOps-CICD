package edu.tus.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import edu.tus.wizworld.controllers.BookStoreController;
import edu.tus.wizworld.dao.BookRepository;
import edu.tus.wizworld.dto.Book;
import edu.tus.wizworld.dto.Rating;
import edu.tus.wizworld.errors.BookValidator;
import edu.tus.wizworld.errors.ErrorMessages;
import edu.tus.wizworld.exceptions.BookValidationException;

public class BookStoreControllerTest {
	@Mock
	BookRepository bookRepo;
	@Mock
	BookValidator bookValidator;
	@InjectMocks
	BookStoreController bookController;

	@BeforeEach
	void setUp() throws IOException {
		MockitoAnnotations.openMocks(this);
	}

	private Long mockId = 100L;
	private Book mockBook = new Book(mockId, "Title", "Author", "IllustratedBy", 10.25, 15.25, "Image", "Series",
			Rating.CLASSIC);

	@Test
	public void getBooksTest() {
		List<Book> mockList = new ArrayList<>();
		Book book = new Book();
		book.setAuthor(mockBook.getAuthor());
		book.setId(mockBook.getId());
		book.setIllustratedBy(mockBook.getIllustratedBy());
		book.setImage(mockBook.getImage());
		book.setOnlinePrice(mockBook.getOnlinePrice());
		book.setRating(mockBook.getRating());
		book.setRealRetailPrice(mockBook.getRealRetailPrice());
		book.setSeries(mockBook.getSeries());
		book.setTitle(mockBook.getTitle());
		mockList.add(book);
		when(bookRepo.findAll()).thenReturn(mockList);
		List<Book> results = bookController.getBooks();
		assertEquals(1, results.size());
	}

	@Test
	public void getBookByIdTest() {
		when(bookRepo.findById(mockId)).thenReturn(Optional.of(mockBook));
		ResponseEntity result = bookController.getBookById(mockId);
		assertEquals(HttpStatus.OK, result.getStatusCode());
	}

	@Test
	public void getBookByIdNotFoundTest() {
		when(bookRepo.findById(100000L)).thenReturn(Optional.empty());
		ResponseEntity result = bookController.getBookById(mockId);
		assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
		assertEquals(ErrorMessages.BOOK_NOT_FOUND.toString(), result.getBody().toString());
	}

	@Test
	public void addBookTest() {
		when(bookRepo.save(mockBook)).thenReturn(mockBook);
		ResponseEntity result = bookController.addBook(mockBook);
		assertEquals(HttpStatus.CREATED, result.getStatusCode());
	}

	@Test
	public void addBookFailureTest() throws BookValidationException {
		doThrow(new BookValidationException(ErrorMessages.EMPTY_FIELDS.getMsg())).when(bookValidator)
				.validateBook(mockBook);
		ResponseEntity result = bookController.addBook(mockBook);
		assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
	}

	@Test
	public void deleteBookTest() {
		when(bookRepo.findById(mockId)).thenReturn(Optional.of(mockBook));
		ResponseEntity result = bookController.deleteBookById(mockId);
		assertEquals(HttpStatus.OK, result.getStatusCode());
	}

	@Test
	public void deleteBookNotFoundTest() {
		when(bookRepo.findById(mockId)).thenReturn(Optional.empty());
		ResponseEntity result = bookController.deleteBookById(mockId);
		assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
	}
}
