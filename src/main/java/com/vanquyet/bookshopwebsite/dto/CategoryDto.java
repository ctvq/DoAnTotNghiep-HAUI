package com.vanquyet.bookshopwebsite.dto;

import com.vanquyet.bookshopwebsite.entity.Category;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link Category}
 */
@Value
public class CategoryDto implements Serializable {
    String name;
    Double totalRevenue;
}