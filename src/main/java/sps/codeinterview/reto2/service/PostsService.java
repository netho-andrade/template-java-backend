package sps.codeinterview.reto2.service;

import java.util.List;

import sps.codeinterview.reto2.model.Posts;

public interface PostsService{
  public List<Posts> getPostsByUser(int userId);
}