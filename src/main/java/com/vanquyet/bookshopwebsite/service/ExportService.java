package com.vanquyet.bookshopwebsite.service;

import com.vanquyet.bookshopwebsite.dto.BookDto;
import com.vanquyet.bookshopwebsite.dto.CategoryDto;
import com.vanquyet.bookshopwebsite.dto.OrderDTO;
import com.vanquyet.bookshopwebsite.entity.User;

import java.util.List;

public interface ExportService {

    String exportOrderReport(User user, List<OrderDTO> orderDTOList, String keyword);

    String exportCategoryReport(User user, List<CategoryDto> categoryDTOList, String keyword);

    String exportBookReport(User user, List<BookDto> bookDtoList, String keyword);

}
