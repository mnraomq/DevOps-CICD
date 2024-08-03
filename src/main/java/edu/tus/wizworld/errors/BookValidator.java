package edu.tus.wizworld.errors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import edu.tus.wizworld.dao.BookRepository;
import edu.tus.wizworld.dto.Book;
import edu.tus.wizworld.exceptions.BookValidationException;

@Service
public class BookValidator {

	@Autowired
	BookRepository bookRepo;

	public void validateBook(Book book) throws BookValidationException {
		checkEmptyFields(book);
		checkIfTitleAlreadyExists(book);
	}

	private void checkEmptyFields(Book book) throws BookValidationException {
		if ((book.getTitle().length() == 0) || (book.getSeries().length() == 0)) {
			throw new BookValidationException(ErrorMessages.EMPTY_FIELDS.getMsg());
		}
	}

	private void checkIfTitleAlreadyExists(Book book) throws BookValidationException {
		Book existingBook = bookRepo.findByTitle(book.getTitle());
		if (existingBook != null) {
			throw new BookValidationException(ErrorMessages.ALREADY_EXISTS.getMsg());
		}
	}
}
