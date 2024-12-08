package com.group7.bookshopwebsite.service;

import com.group7.bookshopwebsite.dto.BookDto;
import com.group7.bookshopwebsite.dto.CategoryDto;
import com.group7.bookshopwebsite.dto.OrderDTO;
import com.group7.bookshopwebsite.entity.User;

import java.util.List;

public interface ExportService {

    String exportOrderReport(User user, List<OrderDTO> orderDTOList, String keyword);

    String exportCategoryReport(User user, List<CategoryDto> categoryDTOList, String keyword);

    String exportBookReport(User user, List<BookDto> bookDtoList, String keyword);

}
