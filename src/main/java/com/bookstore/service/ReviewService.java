package com.bookstore.service;

import com.bookstore.entity.Review;
import com.bookstore.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {
    
    @Autowired
    private ReviewRepository reviewRepository;
    
    public Review saveReview(Review review) {
        return reviewRepository.save(review);
    }
    
    public List<Review> getReviewsByBookId(Long bookId) {
        return reviewRepository.findByBookId(bookId);
    }
    
    public List<Review> getReviewsByUserId(Long userId) {
        return reviewRepository.findByUserId(userId);
    }
    
    public Optional<Review> getUserReviewForBook(Long userId, Long bookId) {
        return reviewRepository.findByUserIdAndBookId(userId, bookId);
    }
    
    public Double getAverageRatingForBook(Long bookId) {
        return reviewRepository.findAverageRatingByBookId(bookId);
    }
    
    public Long getReviewCountForBook(Long bookId) {
        return reviewRepository.countByBookId(bookId);
    }
    
    public void deleteReview(Long reviewId) {
        reviewRepository.deleteById(reviewId);
    }
}