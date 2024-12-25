package com.vanquyet.bookshopwebsite.repository;

import com.vanquyet.bookshopwebsite.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByBlogIdOrderByCreatedAtDesc(Long blogId);

}
