package com.group7.bookshopwebsite.dto;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.group7.bookshopwebsite.entity.Category}
 */
@Value
public class CategoryDto implements Serializable {
    String name;
    Double totalRevenue;
}