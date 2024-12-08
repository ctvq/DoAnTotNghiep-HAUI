package com.group7.bookshopwebsite.dto;

import lombok.Data;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.group7.bookshopwebsite.entity.Book}
 */
@Data
@Value
public class BookDto implements Serializable {
    String title;
    Double totalRevenue;

}