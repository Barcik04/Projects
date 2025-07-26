package com.example.pietnasty_lipiec25.post;

import com.example.pietnasty_lipiec25.jsonplacehonder.JsonPlaceHolderService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@Service
public class PostService {
    private final JsonPlaceHolderService jsonPlaceHolderService;
     public PostService(JsonPlaceHolderService jsonPlaceHolderService) {
            this.jsonPlaceHolderService = jsonPlaceHolderService;
     }

     public List<Post> getAllPosts() {
         return jsonPlaceHolderService.getAllPosts();
     }

     public Post getPostById(Integer id) {
         try {
             return jsonPlaceHolderService.getPostById(id);
         } catch (Exception e) {
             throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Post with id " + id + " not found");
         }
     }

     public Post addPost(Post post) {
         return jsonPlaceHolderService.addPost(post);
     }

     public Post updatePostById(Integer id, Post post) {
         try {
             return jsonPlaceHolderService.updatePostById(id, post);
         } catch (Exception e) {
             throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Post with id " + id + " does not exist");
         }
     }

     public void deletePostById(Integer id) {
         if (jsonPlaceHolderService.getPostById(id) == null) {
             throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Post with id " + id + " does not exist");
         }
     }
}
