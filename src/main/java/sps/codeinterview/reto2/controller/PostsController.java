package sps.codeinterview.reto2.controller;

import sps.codeinterview.reto2.model.Posts;
import sps.codeinterview.reto2.service.PostsService;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PostsController{
  
  @Autowired
  private PostsService postsService;
  
  @GetMapping("/user/{userId}")
  public List<Posts> getListPost(@PathVariable int userId){
    return postsService.getPostsByUser(userId);
  }
}