package com.blog.app.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.util.StreamUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.blog.app.config.ConstantLiterals;
import com.blog.app.payloads.ApiResponse;
import com.blog.app.payloads.PostDto;
import com.blog.app.payloads.PostResponse;
import com.blog.app.services.FileService;
import com.blog.app.services.PostService;

import jakarta.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping(value = "/api/", produces = "application/json")
public class PostController {

	@Autowired
	private PostService postService;

	@Autowired
	private FileService fileService;

	@Value("project.image")
	private String path;

	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable Integer userId,
			@PathVariable Integer categoryId) {

		PostDto createdPost = this.postService.createPost(postDto, userId, categoryId);
		return new ResponseEntity<PostDto>(createdPost, HttpStatus.CREATED);
	}

	// get post by userId
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable Integer userId) {

		List<PostDto> post = this.postService.getPostByUserId(userId);
		System.out.println("Retrieved Posts: " + post);
		return new ResponseEntity<List<PostDto>>(post, HttpStatus.OK);
	}

	// get post by categoryId
	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable Integer categoryId) {

		List<PostDto> post = this.postService.getPostByCategoryId(categoryId);
		return new ResponseEntity<List<PostDto>>(post, HttpStatus.OK);
	}

	// get All Post

	@GetMapping("/getAllPosts")
	public ResponseEntity<PostResponse> getAllPosts(
			@RequestParam(value = "pageNumber", defaultValue = ConstantLiterals.PAGE_NUMBER, required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = ConstantLiterals.PAGE_SIZE, required = false) Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue = ConstantLiterals.SORT_BY, required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = ConstantLiterals.SORT_DIR, required = false) String sortDir)

	{
		 // Debugging logs
	    System.out.println("pageNumber: " + pageNumber);
	    System.out.println("pageSize: " + pageSize);
	    System.out.println("sortBy: " + sortBy);
	    System.out.println("sortDir: " + sortDir);

		PostResponse postReponse = this.postService.getAllPost(pageNumber, pageSize, sortBy, sortDir);

		return new ResponseEntity<PostResponse>(postReponse, HttpStatus.OK);
	}

	// get Post by id

	@GetMapping("/{id}")
	public PostDto getPostById(@PathVariable Integer id) {

		PostDto postDto = this.postService.getPostByID(id);
		return postDto;
	}

	// delete Post by id

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletePostBYId(@PathVariable Integer id) {
		this.postService.deletePost(id);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Post deleted successfully ", true), HttpStatus.OK);

	}

	// update post
	@PutMapping("/{id}")
	public ResponseEntity<PostDto> updatePost(@PathVariable Integer id, @RequestBody PostDto postDto) {
		PostDto updatedPost = this.postService.updatePost(postDto, id);

		return new ResponseEntity<PostDto>(updatedPost, HttpStatus.OK);
	}

	// Search post bt keyword

	@GetMapping("/posts/search/{keyword}")
	public ResponseEntity<List<PostDto>> searchPostByTitle(@PathVariable("keyword") String keyword) {
		List<PostDto> posts = this.postService.getPostBySearch(keyword);
		return new ResponseEntity<List<PostDto>>(posts, HttpStatus.OK);
	}

	// Post method to upload image
	@PostMapping("/post/image/upload/{postId}")
	public ResponseEntity<PostDto> uploadImage(@RequestParam("image") MultipartFile file, @PathVariable Integer postId)
			throws IOException {

		PostDto postDto = this.postService.getPostByID(postId);
		String imageName = this.fileService.uploadImage(path, file);

		postDto.setImageName(imageName);
		PostDto updatedPost = this.postService.updatePost(postDto, postId);
		return new ResponseEntity<PostDto>(updatedPost, HttpStatus.OK);
	}

	// get method for imag serving
	@GetMapping(value = "/post/image/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
	public void downloadImage(@PathVariable String imageName, HttpServletResponse response) throws IOException {

		InputStream ip = this.fileService.getResource(path, imageName);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		org.springframework.util.StreamUtils.copy(ip, response.getOutputStream());
	}

}
