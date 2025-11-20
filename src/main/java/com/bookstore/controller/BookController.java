package com.bookstore.controller;

import com.bookstore.entity.Book;
import com.bookstore.entity.Review;
import com.bookstore.service.BookService;
import com.bookstore.service.RecommendationService;
import com.bookstore.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BookController {
    
    @Autowired
    private BookService bookService;
    
    @Autowired
    private ReviewService reviewService;
    
    @Autowired
    private RecommendationService recommendationService;
    
    @GetMapping
    public String listBooks(@RequestParam(value = "search", required = false) String search, Model model) {
        List<Book> books;
        if (search != null && !search.trim().isEmpty()) {
            books = bookService.searchBooks(search.trim());
            model.addAttribute("searchTerm", search.trim());
        } else {
            books = bookService.getAllBooks();
        }
        model.addAttribute("books", books);
        // This tells Spring to find "list.html" inside "templates/books/"
        return "books/list"; 
    }
    
    @GetMapping("/{id}")
    public String viewBook(@PathVariable Long id, Model model) {
        Optional<Book> bookOpt = bookService.getBookById(id);
        if (bookOpt.isEmpty()) {
            return "redirect:/books";
        }
        
        Book book = bookOpt.get();
        List<Review> reviews = reviewService.getReviewsByBookId(id);
        Double averageRating = reviewService.getAverageRatingForBook(id);
        Long reviewCount = reviewService.getReviewCountForBook(id);
        List<Book> similarBooks = recommendationService.getSimilarBooks(id);
        
        model.addAttribute("book", book);
        model.addAttribute("reviews", reviews);
        model.addAttribute("averageRating", averageRating);
        model.addAttribute("reviewCount", reviewCount);
        model.addAttribute("similarBooks", similarBooks);
        model.addAttribute("newReview", new Review());
        
        // This tells Spring to find "detail.html" inside "templates/books/"
        return "books/detail";
    }
    
    @GetMapping("/genre/{genre}")
    public String booksByGenre(@PathVariable String genre, Model model) {
        List<Book> books = bookService.getBooksByGenre(genre);
        model.addAttribute("books", books);
        model.addAttribute("genre", genre);
        return "books/genre"; // Assumes "templates/books/genre.html"
    }
    
    @GetMapping("/author/{author}")
    public String booksByAuthor(@PathVariable String author, Model model) {
        List<Book> books = bookService.getBooksByAuthor(author);
        model.addAttribute("books", books);
        model.addAttribute("author", author);
        return "books/author"; // Assumes "templates/books/author.html"
    }
}