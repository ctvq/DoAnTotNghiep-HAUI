package com.group7.bookshopwebsite.service.impl;

import com.group7.bookshopwebsite.constant.SortType;
import com.group7.bookshopwebsite.dto.BookDto;
import com.group7.bookshopwebsite.dto.BookSearchDTO;
import com.group7.bookshopwebsite.dto.OrderDTO;
import com.group7.bookshopwebsite.dto.UserSearchDTO;
import com.group7.bookshopwebsite.entity.Book;
import com.group7.bookshopwebsite.entity.Category;
import com.group7.bookshopwebsite.entity.OrderDetail;
import com.group7.bookshopwebsite.entity.User;
import com.group7.bookshopwebsite.repository.BookRepository;
import com.group7.bookshopwebsite.repository.CategoryRepository;
import com.group7.bookshopwebsite.repository.OrderDetailRepository;
import com.group7.bookshopwebsite.repository.UserRepository;
import com.group7.bookshopwebsite.service.BookService;
import com.group7.bookshopwebsite.service.CategoryService;
import com.group7.bookshopwebsite.service.FileUploadService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;

@RequiredArgsConstructor
@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class BookServiceImpl implements BookService {

    BookRepository bookRepository;
    CategoryRepository categoryRepository;
    UserRepository userRepository;
    CategoryService categoryService;
    FileUploadService fileUploadService;
    OrderDetailRepository orderDetailRepository;

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public void addBook(Book book, MultipartFile coverImage) throws IOException {
        Book savedBook = bookRepository.save(book);
        savedBook.setCoverImage(fileUploadService.uploadFile(coverImage));
        bookRepository.save(book);
    }

    @Override
    public void editBook(Book book, MultipartFile coverImage) throws IOException {
        Book savedBook = bookRepository.save(book);
        if (!coverImage.isEmpty()) {
            savedBook.setCoverImage(fileUploadService.uploadFile(coverImage));
            bookRepository.save(book);
        }
    }

    @Override
    public void deleteBook(Long id) throws Exception {
        List<OrderDetail> orderDetailsFindByBookId = orderDetailRepository.findByBookId((id));
        if(!orderDetailsFindByBookId.isEmpty()){
            throw new Exception("Sách đã có trong các đơn hàng , vui lòng xoá các đơn hàng có sách trước");
        }
        bookRepository.deleteById(id);
    }

    @Override
    public Book getBookById(Long id) {
        Optional<Book> bookOptional = bookRepository.findById(id);
        return bookOptional.orElse(null);
    }

    @Override
    public Book getBookByName(String name) {
        return bookRepository.findByTitle(name);
    }
    private String generateUniqueFileName(String originalFileName) {
        String timestamp = String.valueOf(System.currentTimeMillis());
        return timestamp + "_" + originalFileName;
    }


    @Override
    public Page<Book> searchBooks(BookSearchDTO search, Pageable pageable) {
        Long categoryId = search.getCategoryId();
        String keyword = search.getKeyword();

        // Lấy dữ liệu phân trang dựa trên categoryId, keyword và các điều kiện tìm kiếm khác (nếu có)
        if (categoryId != null && keyword != null) {
            return bookRepository.findByCategory_IdAndTitleContaining(categoryId, keyword, pageable);
        } else if (categoryId != null) {
            Category category = categoryRepository.findById(categoryId).orElse(null);
            return bookRepository.findByCategory(category, pageable);

        } else if (keyword != null) {
            return bookRepository.findByTitleContaining(keyword, pageable);
        } else {
            return bookRepository.findAll(pageable);
        }
    }

    @Override
    public Page<Book> searchBooksUser(UserSearchDTO search, Pageable pageable) {
        Long categoryId = search.getCategoryId();
        String keyword = search.getKeyword();
        if (keyword == null) keyword = "";
        String sortBy = search.getSortBy();
        String amountGap = search.getAmountGap();
        String[] temp = amountGap.split(" ");
        Double startAmount = Double.parseDouble(temp[0].replace(".", ""));
        Double endAmount = Double.parseDouble(temp[3].replace(".", ""));


        Page<Book> booksPage = bookRepository.findAll(pageable);
        if (categoryId != null) {
            if (sortBy.equals(SortType.oldest)) {
                booksPage = bookRepository.findByCategoryIdAndTitleContainingAndOriginalPriceBetweenOrderByCreatedAtAsc(categoryId, keyword, startAmount, endAmount, pageable);
            } else if (sortBy.equals(SortType.newest)) {
                booksPage = bookRepository.findByCategoryIdAndTitleContainingAndOriginalPriceBetweenOrderByCreatedAtDesc(categoryId, keyword, startAmount, endAmount, pageable);

            } else if (sortBy.equals(SortType.priceLowToHigh)) {
                booksPage = bookRepository.findByCategoryIdAndTitleContainingAndOriginalPriceBetweenOrderBySalePriceAsc(categoryId, keyword, startAmount, endAmount, pageable);

            } else {
                booksPage = bookRepository.findByCategoryIdAndTitleContainingAndOriginalPriceBetweenOrderBySalePriceDesc(categoryId, keyword, startAmount, endAmount, pageable);
            }
        } else {
            if (sortBy.equals(SortType.oldest)) {
                booksPage = bookRepository.findByTitleContainingAndOriginalPriceBetweenOrderByCreatedAtAsc(keyword, startAmount, endAmount, pageable);
            } else if (sortBy.equals(SortType.newest)) {
                booksPage = bookRepository.findByTitleContainingAndOriginalPriceBetweenOrderByCreatedAtDesc(keyword, startAmount, endAmount, pageable);

            } else if (sortBy.equals(SortType.priceLowToHigh)) {
                booksPage = bookRepository.findByTitleContainingAndOriginalPriceBetweenOrderBySalePriceAsc(keyword, startAmount, endAmount, pageable);

            } else {
                booksPage = bookRepository.findByTitleContainingAndOriginalPriceBetweenOrderBySalePriceDesc(keyword, startAmount, endAmount, pageable);
            }
        }
        if (sortBy == null) {
            booksPage = bookRepository.findByCategoryIdAndTitleContainingAndOriginalPriceBetweenOrderByCreatedAtDesc(categoryId, keyword, startAmount, endAmount, pageable);
        }
        return booksPage;
    }


    @Override
    public Page<Book> getAllBooksForUsers(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    @Override
    public List<Book> getTop4BestSeller() {
        return bookRepository.findTop4ByOrderByBuyCountDesc();
    }

    @Override
    public List<Book> getTop10BestSeller() {
        return bookRepository.findTop10ByOrderByTotalRevenueDesc();
    }

    @Override
    public List<BookDto> getTop10BestSellerByMonth(int month) {
        List<Object[]> result = bookRepository.findTop10BestSellerByMonth(month);
        List<BookDto> resultConvertedToDto = new ArrayList<>();
        for (Object[] item : result) {
            resultConvertedToDto.add(new BookDto(item[0].toString(), Double.parseDouble(item[1].toString())));
        }
        return resultConvertedToDto;
    }

    @Override
    public List<Book> findAllOrderByCreatedDate() {
        return bookRepository.findByOrderByCreatedAtDesc();
    }

    @Override
    public Set<Book> getFavoriteBooksByUserId(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            return user.getFavoriteBooks();
        }
        return Collections.emptySet();
    }

    @Override
    public Long countBook() {
        return bookRepository.count();
    }

    @Override
    public List<Book> getAllBooksByCategoryId(Long id) {
        Category category = categoryRepository.findById(id).orElse(null);
        return bookRepository.findAllByCategory(category);
    }

    @Override
    public Page<Book> getAllBooksPaginatedByCategoryId(Long categoryId, Pageable pageable) {
        return bookRepository.findByCategoryId(categoryId, pageable);
    }


}
