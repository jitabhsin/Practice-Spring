package com.example.GraphQl.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.example.GraphQl.entity.*;
import com.example.GraphQl.repository.*;
@Service
public class BookService {
       
	@Autowired
	private  BookRepository repository;
	
	
	public Book createBook( String title , String author , Double price) {
           
		
		return repository.save( new Book ( title , author , price));
	}
	
	public  List<Book> getAllBook(){
		return repository.findAll();
	}
	
	public Book getBookById( long id) {
		return repository.findById(id).orElse(null);
	}
	public void deleteBook( Long id) {
		repository.deleteById(id);
	}
	
	public Book updateBook( Long id , String title, String author , Double price) {
	
	Book book = repository.findById(id).orElse(null);
	
	if(title != null) book.setTitle(title);
	if(author != null) book.setAuthor(author);
	if(price != null) book.setPrice(price);
	return repository.save(book);
	}
	
}
