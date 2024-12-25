package com.vanquyet.bookshopwebsite.controller;

import com.vanquyet.bookshopwebsite.controller.common.BaseController;
import com.vanquyet.bookshopwebsite.entity.Book;
import com.vanquyet.bookshopwebsite.entity.User;
import com.vanquyet.bookshopwebsite.service.BookService;
import com.vanquyet.bookshopwebsite.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Controller
@AllArgsConstructor
@RequestMapping("/wishlist")
public class WishListController extends BaseController {

    private final UserService userService;
    private final BookService bookService;

    @PostMapping("/add")
    @ResponseBody
    public ResponseEntity<String> addToWishList(@RequestParam Long bookId) {
        User currentUser = getCurrentUser();
        Book book = bookService.getBookById(bookId);

        if (book != null) {
            userService.addBookToUser(currentUser.getId(), bookId);
            return ResponseEntity.ok("ok");
        }

        return ResponseEntity.badRequest().body("Book not found");
    }

    @PostMapping("/remove")
    @ResponseBody
    public ResponseEntity<String> removeFromWishList(@RequestParam Long bookId) {
        User currentUser = getCurrentUser();
        Book book = bookService.getBookById(bookId);

        if (book != null) {
            userService.removeBookFromUser(currentUser.getId(), bookId);
            return ResponseEntity.ok("ok");
        }

        return ResponseEntity.badRequest().body("Book not found");
    }

    @GetMapping
    public String getWishList(Model model) {
        Set<Book> favoritesList = bookService.getFavoriteBooksByUserId(getCurrentUser().getId());
        model.addAttribute("favoritesList", favoritesList);
        return "user/wishlist";
    }


}