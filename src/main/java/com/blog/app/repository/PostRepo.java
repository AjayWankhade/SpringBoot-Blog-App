package com.blog.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.blog.app.entities.Category;
import com.blog.app.entities.Post;
import com.blog.app.entities.User;
import com.blog.app.payloads.PostDto;

public interface PostRepo extends JpaRepository<Post, Integer> {

	List<Post> findAllByUser(User user);

	List<Post> findAllByCategory(Category cat);

	@Query("SELECT p FROM Post p WHERE LOWER(p.title) LIKE LOWER(CONCAT('%', :key, '%'))")
	List<Post> searchByTitle(@Param("key") String title);

}
