package com.bookstore.config;

import com.bookstore.entity.Book;
import com.bookstore.entity.User;
import com.bookstore.repository.BookRepository;
import com.bookstore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import java.util.Arrays;
import com.bookstore.entity.Role;

@Component
public class DataInitializer implements CommandLineRunner {
    
    @Autowired
    private BookRepository bookRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Override
    public void run(String... args) throws Exception {
        // Initialize sample books
        if (bookRepository.count() == 0) {
            
            // --- THIS IS THE CORRECTED SECTION ---
            
            Book book1 = new Book("The Great Gatsby", "F. Scott Fitzgerald", "9780743273565", 
                            "A classic novel of the Jazz Age", new BigDecimal("12.99"), 50, "Fiction");
            book1.setCoverImage("https://covers.openlibrary.org/b/isbn/9780743273565-L.jpg");

            Book book2 = new Book("To Kill a Mockingbird", "Harper Lee", "9780061120084", 
                            "A gripping story of racial injustice", new BigDecimal("14.99"), 30, "Fiction");
            book2.setCoverImage("https://covers.openlibrary.org/b/isbn/9780061120084-L.jpg");

            Book book3 = new Book("1984", "George Orwell", "9780451524935", 
                            "A dystopian social science fiction novel", new BigDecimal("10.99"), 25, "Science Fiction");
            book3.setCoverImage("https://covers.openlibrary.org/b/isbn/9780451524935-L.jpg");

            Book book4 = new Book("Pride and Prejudice", "Jane Austen", "9780141439518", 
                            "A romantic novel of manners", new BigDecimal("9.99"), 40, "Romance");
            book4.setCoverImage("https://covers.openlibrary.org/b/isbn/9780141439518-L.jpg");

            Book book5 = new Book("The Hobbit", "J.R.R. Tolkien", "9780547928227", 
                            "A fantasy novel and children's book", new BigDecimal("15.99"), 35, "Fantasy");
            book5.setCoverImage("https://covers.openlibrary.org/b/isbn/9780547928227-L.jpg");

            Book book6 = new Book("Harry Potter and the Sorcerer's Stone", "J.K. Rowling", "9780590353427", 
                            "The first novel in the Harry Potter series", new BigDecimal("18.99"), 60, "Fantasy");
            book6.setCoverImage("https://covers.openlibrary.org/b/isbn/9780590353427-L.jpg");

            Book book7 = new Book("The Catcher in the Rye", "J.D. Salinger", "9780316769174", 
                            "A controversial novel originally published for adults", new BigDecimal("11.99"), 20, "Fiction");
            book7.setCoverImage("https://covers.openlibrary.org/b/isbn/9780316769174-L.jpg");

            Book book8 = new Book("The Lord of the Rings", "J.R.R. Tolkien", "9780544003415", 
                            "An epic high fantasy novel", new BigDecimal("24.99"), 15, "Fantasy");
            book8.setCoverImage("https://covers.openlibrary.org/b/isbn/9780544003415-L.jpg");


            Book book9 = new Book("The Da Vinci Code", "Dan Brown", "9780307474278", 
                            "A mystery thriller novel", new BigDecimal("13.99"), 45, "Mystery");
            book9.setCoverImage("https://covers.openlibrary.org/b/isbn/9780307474278-L.jpg");

            Book book10 = new Book("The Alchemist", "Paulo Coelho", "9780061122415", 
                            "A philosophical book", new BigDecimal("10.99"), 55, "Philosophy");
            book10.setCoverImage("https://covers.openlibrary.org/b/isbn/9780061122415-L.jpg");
            
            // Now, save all the books
            bookRepository.saveAll(Arrays.asList(book1, book2, book3, book4, book5, book6, book7, book8, book9, book10));
        }
        
        // Initialize admin user
        if (userRepository.findByUsername("admin").isEmpty()) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setEmail("admin@bookstore.com");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setFirstName("Admin");
            admin.setLastName("User");
            admin.setRole(Role.ADMIN);
            userRepository.save(admin);
        }
    }
}