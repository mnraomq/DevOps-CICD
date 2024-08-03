package edu.tus.wizworld.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import edu.tus.wizworld.dto.Book;
import edu.tus.wizworld.dto.Rating;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

	//TO DO
	
	List<Book> findByRating(Rating rating);

	List<Book> findByOnlinePriceBetween(double min, double max);
	
	Book findByTitle(String title);

	
}
