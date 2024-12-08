package com.group7.bookshopwebsite.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "categories")
public class Category extends AbstractBase {

    @Column(name = "name")
    private String name;

    @Lob
    private String description;

    @JsonIgnore
    @OneToMany(mappedBy = "category",
            cascade = {CascadeType.ALL})
    private List<Book> bookList;

    // Getters and setters, validation annotations, and other fields as needed
}