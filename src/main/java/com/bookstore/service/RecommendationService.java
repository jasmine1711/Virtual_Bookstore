package com.bookstore.service;

import com.bookstore.entity.Book;
import com.bookstore.entity.User;
import com.bookstore.entity.Review;
import com.bookstore.repository.BookRepository;
import com.bookstore.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class RecommendationService {
    
    @Autowired
    private BookRepository bookRepository;
    
    @Autowired
    private ReviewRepository reviewRepository;
    
    public List<Book> getRecommendedBooks(User user) {
        // Simple collaborative filtering based on user preferences
        List<Review> userReviews = reviewRepository.findByUserId(user.getId());
        
        if (userReviews.isEmpty()) {
            // If no reviews, return popular books
            return getPopularBooks();
        }
        
        // Get user's preferred genres
        Map<String, Long> genrePreferences = userReviews.stream()
            .collect(Collectors.groupingBy(
                review -> review.getBook().getGenre(),
                Collectors.counting()
            ));
        
        // Get top 3 preferred genres
        List<String> preferredGenres = genrePreferences.entrySet().stream()
            .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
            .limit(3)
            .map(Map.Entry::getKey)
            .collect(Collectors.toList());
        
        // Get books from preferred genres that user hasn't reviewed
        List<Book> recommendedBooks = new ArrayList<>();
        for (String genre : preferredGenres) {
            List<Book> genreBooks = bookRepository.findByGenreContainingIgnoreCase(genre);
            for (Book book : genreBooks) {
                if (userReviews.stream().noneMatch(review -> review.getBook().getId().equals(book.getId()))) {
                    recommendedBooks.add(book);
                }
            }
        }
        
        // If not enough recommendations, add popular books
        if (recommendedBooks.size() < 5) {
            recommendedBooks.addAll(getPopularBooks().stream()
                .filter(book -> recommendedBooks.stream().noneMatch(rb -> rb.getId().equals(book.getId())))
                .limit(5 - recommendedBooks.size())
                .collect(Collectors.toList()));
        }
        
        return recommendedBooks.stream().distinct().limit(10).collect(Collectors.toList());
    }
    
    public List<Book> getPopularBooks() {
        // Simple implementation - return books with highest average ratings
        List<Book> allBooks = bookRepository.findAll();
        
        return allBooks.stream()
            .sorted((b1, b2) -> {
                Double rating1 = reviewRepository.findAverageRatingByBookId(b1.getId());
                Double rating2 = reviewRepository.findAverageRatingByBookId(b2.getId());
                return Double.compare(rating2 != null ? rating2 : 0, rating1 != null ? rating1 : 0);
            })
            .limit(10)
            .collect(Collectors.toList());
    }
    
    public List<Book> getSimilarBooks(Long bookId) {
        Optional<Book> bookOpt = bookRepository.findById(bookId);
        if (bookOpt.isEmpty()) {
            return new ArrayList<>();
        }
        
        Book book = bookOpt.get();
        return bookRepository.findByGenreContainingIgnoreCase(book.getGenre()).stream()
            .filter(b -> !b.getId().equals(bookId))
            .limit(6)
            .collect(Collectors.toList());
    }
}