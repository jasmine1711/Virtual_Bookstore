package com.bookstore.service;

import com.bookstore.entity.Book;
import com.bookstore.entity.Order;
import com.bookstore.entity.OrderItem;
import com.bookstore.entity.User;
import com.bookstore.entity.OrderStatus;
import com.bookstore.repository.BookRepository;
import com.bookstore.repository.OrderRepository;
import com.bookstore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CartService {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;

    private List<CartItem> items = new ArrayList<>();

    public void addBook(Long bookId) {
        Optional<Book> bookOpt = bookRepository.findById(bookId);
        if (bookOpt.isPresent()) {
            Book book = bookOpt.get();
            // Check if already in cart
            for (CartItem item : items) {
                if (item.getBook().getId().equals(bookId)) {
                    item.setQuantity(item.getQuantity() + 1);
                    return;
                }
            }
            items.add(new CartItem(book, 1));
        }
    }

    public void removeBook(Long bookId) {
        items.removeIf(item -> item.getBook().getId().equals(bookId));
    }

    public List<CartItem> getItems() { return items; }
    public void clearCart() { items.clear(); }

    // Returns BigDecimal now
    public BigDecimal getTotal() {
        return items.stream()
            .map(item -> {
                BigDecimal price = item.getBook().getPrice(); // Assuming Book price is BigDecimal
                return price.multiply(BigDecimal.valueOf(item.getQuantity()));
            })
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

  @Transactional
    public void checkout(String username) {
        // We use findByUsername because principal.getName() is the username.
        // We try username first, then email as a backup.
        User user = userRepository.findByUsername(username)
                .or(() -> userRepository.findByEmail(username))
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        Order order = new Order();
        order.setUser(user);
        order.setStatus(OrderStatus.PENDING);
        order.setTotalAmount(getTotal());
        
        List<OrderItem> orderItems = new ArrayList<>();
        for (CartItem cartItem : items) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setBook(cartItem.getBook());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setPrice(cartItem.getBook().getPrice());
            orderItems.add(orderItem);
        }
        order.setOrderItems(orderItems);
        
        orderRepository.save(order);
        items.clear();
    }

    // Inner class
    public static class CartItem {
        private Book book;
        private int quantity;
        public CartItem(Book book, int quantity) { this.book = book; this.quantity = quantity; }
        public Book getBook() { return book; }
        public int getQuantity() { return quantity; }
        public void setQuantity(int q) { this.quantity = q; }
    }
}