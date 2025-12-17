package com.example.GraphQl.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

import com.example.GraphQl.entity.*;
import com.example.GraphQl.service.*;

@Controller
//@CrossOrigin(origins = "http://localhost:5173")
public class BookController {
     
	@Autowired
	private BookService service;
	
	
	@QueryMapping
	public List<Book> getAllBooks(){
		return service.getAllBook();
	}
	
	@QueryMapping
	public Book getBookById(@Argument Long id) {
		return service.getBookById(id);
	}
	@MutationMapping
    public Book updateBook(@Argument Long id, 
                           @Argument String title, 
                           @Argument String author, 
                           @Argument Double price) {
        return service.updateBook(id, title, author, price);
    }
	@MutationMapping
	public Book createBook( @Argument String title , @Argument String author  , @Argument  Double price) {
		
		 return service.createBook( title , author , price);
	}
	@MutationMapping
    public String deleteBook(@Argument Long id) {
        service.deleteBook(id); // Assuming your Service has a void deleteBook(Long id)
        return "Book Deleted Successfully";
    }
}
