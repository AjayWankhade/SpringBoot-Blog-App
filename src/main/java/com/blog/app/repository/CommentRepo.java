package com.blog.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.app.entities.Comment;

public interface CommentRepo extends JpaRepository<Comment, Integer> {

}
