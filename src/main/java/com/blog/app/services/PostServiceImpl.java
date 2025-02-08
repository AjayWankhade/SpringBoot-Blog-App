package com.blog.app.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.blog.app.entities.Category;
import com.blog.app.entities.Post;
import com.blog.app.entities.User;
import com.blog.app.exceptions.ResourceNotFoundException;
import com.blog.app.payloads.PostDto;
import com.blog.app.payloads.PostResponse;
import com.blog.app.repository.CategoryRepo;
import com.blog.app.repository.PostRepo;
import com.blog.app.repository.UserRepo;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepo postRepo;

	@Autowired
	private CategoryRepo categoryRepo;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {

		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId));
		Category category = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("category", "categoryId", categoryId));

		Post post = this.modelMapper.map(postDto, Post.class);

		post.setImageName("default.png");
		post.setAddedDate(new Date());
		post.setUser(user);
		post.setCategory(category);

		Post newPost = this.postRepo.save(post);
		return modelMapper.map(newPost, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer id) {
		Post post = this.postRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("post", "id", id));

		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setImageName(postDto.getImageName());

		Post updatedPost = this.postRepo.save(post);

		return this.modelMapper.map(updatedPost, PostDto.class);
	}

	@Override
	public PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {

		Sort sort = (sortDir != null && sortDir.equalsIgnoreCase("asc")) ? Sort.by(sortBy).ascending()
				: Sort.by(sortBy).descending();

		Pageable page = PageRequest.of(pageNumber, pageSize, sort);
		Page<Post> pagePost = this.postRepo.findAll(page);
		List<Post> allPosts = pagePost.getContent();

		List<PostDto> postDtos = allPosts.stream().map((post) -> this.modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());

		PostResponse postResonse = new PostResponse();

		postResonse.setContent(postDtos);
		postResonse.setPageNumber(pagePost.getNumber());
		postResonse.setPageSize(pagePost.getSize());
		postResonse.setTotalElements(pagePost.getTotalElements());

		postResonse.setTotalPages(pagePost.getTotalPages());

		postResonse.setLastPage(pagePost.isLast());

		return postResonse;
	}

	@Override
	public PostDto getPostByID(Integer id) {

		Optional<Post> post = this.postRepo.findById(id);
		PostDto postDto = this.modelMapper.map(post, PostDto.class);

		return postDto;
	}

	@Override
	public void deletePost(Integer id) {
		Post post = this.postRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("post", "id", id));
		this.postRepo.delete(post);
	}

	@Override
	public List<PostDto> getPostByCategoryId(Integer id) {

		Category cat = this.categoryRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("category", "id", id));
		List<Post> posts = this.postRepo.findAllByCategory(cat);

		List<PostDto> postDtos = posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());

		return postDtos;
	}

	@Override
	public List<PostDto> getPostByUserId(Integer userId) {
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("user", "userId", userId));

		List<Post> posts = this.postRepo.findAllByUser(user);

		List<PostDto> postDtos = posts.stream().map(post -> this.modelMapper.map(post, PostDto.class)) // Fixed mapping
				.collect(Collectors.toList());

		return postDtos;
	}

	@Override
	public List<PostDto> getPostBySearch(String search) {
		System.out.println("🔍 Received Search Keyword: " + search);

		String wildcardSearch = "%" + search + "%";
		System.out.println("🔍 Wildcard Search Query: " + wildcardSearch);

		List<Post> posts = this.postRepo.searchByTitle(search); // Pass search directly

		System.out.println("✅ Posts Found: " + posts.size());

		return posts.stream().map(post -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
	}

}
