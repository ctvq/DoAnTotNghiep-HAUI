package com.group7.bookshopwebsite.controller.rest.impl;

import com.group7.bookshopwebsite.controller.rest.IGetRevenueController;
import com.group7.bookshopwebsite.controller.rest.base.RestApiV1;
import com.group7.bookshopwebsite.controller.rest.base.VsResponseUtil;
import com.group7.bookshopwebsite.dto.BookDto;
import com.group7.bookshopwebsite.dto.CategoryDto;
import com.group7.bookshopwebsite.service.BookService;
import com.group7.bookshopwebsite.service.CategoryService;
import com.group7.bookshopwebsite.service.OrderService;
import com.group7.bookshopwebsite.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.List;

@RestApiV1
@AllArgsConstructor
public class GetRevenueControllerImpl implements IGetRevenueController {

    private BookService bookService;
    private CategoryService categoryService;

    @Override
    public ResponseEntity<?> getProductRevenueByMonth(@PathVariable("selectedMonth") int selectedMonth) throws UnsupportedEncodingException {
        return VsResponseUtil.ok(HttpStatus.OK, bookService.getTop10BestSellerByMonth(selectedMonth));
    }

    @Override
    public ResponseEntity<?> getCategoryRevenueByMonth(@PathVariable("selectedMonth") int selectedMonth) throws UnsupportedEncodingException {
        return VsResponseUtil.ok(HttpStatus.OK, categoryService.getTop10BestSellerByMonth(selectedMonth));
    }

}
