package com.vanquyet.bookshopwebsite.controller;

import com.vanquyet.bookshopwebsite.controller.common.BaseController;
import com.vanquyet.bookshopwebsite.entity.Book;
import com.vanquyet.bookshopwebsite.service.BookService;
import com.vanquyet.bookshopwebsite.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


@AllArgsConstructor
@Controller

public class HomeController extends BaseController {

    private BookService bookService;
    private CategoryService categoryService;

    @GetMapping("/")
    String getUserHomePage(Model model) {

        List<Book> top4BestSeller = bookService.getTop4BestSeller();
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("top4BestSeller", top4BestSeller);
        List<Book> newProducts = bookService.findAllOrderByCreatedDate();
        model.addAttribute("newProducts", newProducts);
        return "user/index";
    }


}
