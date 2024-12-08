package com.group7.bookshopwebsite.controller.rest.impl;

import com.group7.bookshopwebsite.controller.rest.ICommentController;
import com.group7.bookshopwebsite.controller.rest.base.RestApiV1;
import com.group7.bookshopwebsite.controller.rest.base.VsResponseUtil;
import com.group7.bookshopwebsite.dto.CommentDto;
import com.group7.bookshopwebsite.entity.Comment;
import com.group7.bookshopwebsite.service.CommentService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CommentControllerImpl implements ICommentController {

    CommentService commentService;

    @Override
    public ResponseEntity<?> getCommentHistory() {
        return VsResponseUtil.ok(HttpStatus.OK, commentService.getAll());
    }


    @MessageMapping("/comment")
    @SendTo("/blog/comments")
    public Comment sendComment(CommentDto comment) {
        return commentService.saveComment(comment.getContent(), comment.getBlogId(), comment.getUserId());
    }

}
