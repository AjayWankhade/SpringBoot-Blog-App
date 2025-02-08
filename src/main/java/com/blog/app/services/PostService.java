package com.blog.app.services;

import java.util.List;

import com.blog.app.entities.Post;
import com.blog.app.payloads.PostDto;
import com.blog.app.payloads.PostResponse;

public interface PostService {

	PostDto createPost(PostDto postDto,Integer userId,Integer categoryId);

	PostDto updatePost(PostDto postDto, Integer id);

	PostResponse getAllPost(Integer pageNumber,Integer pageSize,String sortBy,String sortDir);

	PostDto getPostByID(Integer id);

	void deletePost(Integer id);
	
	//getALL post by category id
	
	List<PostDto> getPostByCategoryId(Integer id);
	
	//getALL post by user id
	
	List<PostDto> getPostByUserId(Integer userId);
	
	
	//By serach
	List<PostDto> getPostBySearch(String search);
}
