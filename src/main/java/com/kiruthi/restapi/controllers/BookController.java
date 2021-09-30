package com.kiruthi.restapi.controllers;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.kiruthi.restapi.entities.Book;
import com.kiruthi.restapi.entities.User;
import com.kiruthi.restapi.exceptions.UserNotFoundException;
import com.kiruthi.restapi.repositories.BookRepository;
import com.kiruthi.restapi.repositories.UserRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/users")
public class BookController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository BookRepository;

    @GetMapping("/Books")
    public CollectionModel<Book> getAllBooks() throws UserNotFoundException {

        List<Book> books = BookRepository.findAll();
        for (final Book book : books) {
            Link selfLink = linkTo(methodOn(BookController.class)
                .getOrderById(book.getBookid())).withSelfRel();
            book.add(selfLink);
            Link userLink = linkTo(methodOn(UserController.class)
                .getUserById(book.getUser().getUserid())).withRel("userLink");
            book.add(userLink);
        }
        Link link = linkTo(BookController.class).slash("/Books").withSelfRel();
        CollectionModel<Book> result = CollectionModel.of(books, link);
        return result;
    }

    @GetMapping("/Books/{id}")
    public Book getOrderById(@PathVariable Long id) throws UserNotFoundException {
        return BookRepository.findById(id).get();
    }

     @PostMapping("{userid}/Books")
    public Book createBook(@PathVariable Long userid, @RequestBody Book Book)
        throws UserNotFoundException {
        Optional<User> userOptional = userRepository.findByUserid(userid);

        if (!userOptional.isPresent()) {
            throw new UserNotFoundException("User Not Found");
        }

        User user = userOptional.get();
        Book.setUser(user);
        return BookRepository.save(Book);

    }

}
