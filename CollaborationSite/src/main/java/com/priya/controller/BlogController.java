package com.priya.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.priya.model.BlogBasicModel;
import com.priya.model.BlogViewModel;
import com.priya.model.Blogs;
import com.priya.model.CommentModel;
import com.priya.model.Comments;
import com.priya.model.UserProfiles;
import com.priya.services.BlogService;
import com.priya.services.CommentService;
import com.priya.services.UserProfileService;

@RestController
public class BlogController {

	@Autowired
	private BlogService blogService;
	
	
	@Autowired
	private UserProfileService userProfileService;
	
	
	@Autowired
	private CommentService commentService;
	
	@RequestMapping(value = "all/blogs/all")	
	public  List<BlogBasicModel> getAllBlogs() {				
		List<BlogBasicModel> blogListModel = new ArrayList<BlogBasicModel>();
		BlogBasicModel blogBasicModel = null;
		UserProfiles profile = null;
		
		List<Blogs> blogs = this.blogService.getAll();
		for(Blogs blog: blogs) {			
			profile = this.userProfileService.get(blog.getUserId());
			blogBasicModel = new BlogBasicModel(blog, profile);			
			blogListModel.add(blogBasicModel);			
		}	
		System.out.println("in controllleeeeeeeeeeeer");
		return blogListModel;		
	}
		
	
	@RequestMapping(value = "user/blogs/blogs/{id}")	
	public  List<Blogs> getUserBlogs(@PathVariable("id") String userId) {		
		return this.blogService.getBlogsByUser(userId);		
	}
	
	@RequestMapping(value = "view/blog/blog/{id}")	
	
	public BlogViewModel getBlog(@PathVariable("id") String blogId) {	
		System.out.println("helooooooo.....");
		BlogViewModel blogViewModel = null;	
		Blogs blog = this.blogService.get(blogId);
		UserProfiles profile = this.userProfileService.get(blog.getUserId());
		blogViewModel = new BlogViewModel(blog, profile);
		
		CommentModel commentModel = null;
		
		// Get all the comments
		List<Comments> comments = this.commentService.getComments(blogId);
		if(comments!=null) {
			for(Comments comment : comments) {
				profile = this.userProfileService.get(comment.getUserId());					
				commentModel = new CommentModel(comment, profile);
				blogViewModel.add(commentModel);
			}		
		}	
		return blogViewModel;		
	}
	@RequestMapping(value = "user/blogs/blogs/remove/{id}")	
	public  void delete(@PathVariable("id") String blogId) {	
		System.out.println("deleting......");
		this.blogService.remove(this.blogService.get(blogId));
	}
	


}
