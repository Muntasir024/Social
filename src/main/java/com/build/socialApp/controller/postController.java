package com.build.socialApp.controller;

import com.build.socialApp.Entity.Post;
import com.build.socialApp.service.postService;

import java.util.ArrayList;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/api/postService")
public class postController {
	
	@Autowired
	private final postService postService;
	public postController(postService postService) {
		this.postService = postService;
	}

	@PostMapping("/save")
	public ArrayList<Post> submitPost(@RequestBody Post body){
		ArrayList<Post> result= postService.submitPostToDB(body);
		return result;
	}
	
	@GetMapping("/getPost")
	public ArrayList<Post> retrieveAllPost(){
		ArrayList<Post> result= postService.retrivePostFromDB();
		result.sort((e1, e2) -> e2.getDateTime().compareTo(e1.getDateTime()));
		return result;
	}
	
	@DeleteMapping("/delete/{postId}")
	public ArrayList<Post> deleteParticularPost(@PathVariable("postId") UUID postID){
		ArrayList<Post> result= postService.deletePostFromDB(postID);
		return result;
	}
}
