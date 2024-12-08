package com.group7.bookshopwebsite.repository;

import com.group7.bookshopwebsite.entity.Book;
import com.group7.bookshopwebsite.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    Page<Book> findByTitleContaining(String keyword, Pageable pageable);

    Book findByTitle(String title);

    Page<Book> findByCategory(Category category, Pageable pageable);

    Page<Book> findByCategoryIdAndOriginalPriceBetween(Long categoryId, double minPrice, double maxPrice, Pageable pageable);
    Page<Book> findByCategory_IdAndTitleContainingAndOriginalPriceBetween(Long categoryId, String keyword, double minPrice, double maxPrice, Pageable pageable);

    Page<Book> findByCategoryIdAndTitleContainingAndOriginalPriceBetweenOrderBySalePriceDesc(Long categoryId, String keyword, double minPrice, double maxPrice, Pageable pageable);

    Page<Book> findByCategoryIdAndTitleContainingAndOriginalPriceBetweenOrderBySalePriceAsc(Long categoryId, String keyword, double minPrice, double maxPrice, Pageable pageable);

    Page<Book> findByCategoryIdAndTitleContainingAndOriginalPriceBetweenOrderByCreatedAtAsc(Long categoryId, String keyword, double minPrice, double maxPrice, Pageable pageable);

    Page<Book> findByCategoryIdAndTitleContainingAndOriginalPriceBetweenOrderByCreatedAtDesc(Long categoryId, String keyword, double minPrice, double maxPrice, Pageable pageable);


    Page<Book> findByTitleContainingAndOriginalPriceBetweenOrderByCreatedAtAsc(String title,double minPrice, double maxPrice, Pageable pageable);

    Page<Book> findByTitleContainingAndOriginalPriceBetweenOrderByCreatedAtDesc(String title,double minPrice, double maxPrice, Pageable pageable);

    Page<Book> findByTitleContainingAndOriginalPriceBetweenOrderBySalePriceAsc(String title,double minPrice, double maxPrice, Pageable pageable);

    Page<Book> findByTitleContainingAndOriginalPriceBetweenOrderBySalePriceDesc(String title,double minPrice, double maxPrice, Pageable pageable);


    List<Book> findTop4ByOrderByBuyCountDesc();

    List<Book> findByOrderByCreatedAtDesc();

    List<Book> findTop10ByOrderByTotalRevenueDesc();

    @Query(value = "SELECT b.title, SUM(od.price * od.quantity) AS total_revenue FROM books b " +
            "JOIN order_details od ON b.id = od.book_id " +
            "JOIN orders o ON od.order_id = o.id " +
            "WHERE MONTH(o.create_date) = :month " +
            "GROUP BY b.title " +
            "ORDER BY total_revenue " +
            "DESC " +
            "LIMIT 10", nativeQuery = true)
    List<Object[]> findTop10BestSellerByMonth(@Param("month") int month);

    List<Book> findAllByCategory(Category category);

    Page<Book> findByCategoryId(Long categoryId, Pageable pageable);

    Page<Book> findByCategory_IdAndTitleContaining(Long categoryId, String keyword, Pageable pageable);
}