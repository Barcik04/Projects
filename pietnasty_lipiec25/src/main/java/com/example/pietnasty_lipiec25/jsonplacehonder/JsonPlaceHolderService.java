package com.example.pietnasty_lipiec25.jsonplacehonder;

import com.example.pietnasty_lipiec25.post.Post;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.service.annotation.DeleteExchange;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.PostExchange;
import org.springframework.web.service.annotation.PutExchange;

import java.util.List;

public interface JsonPlaceHolderService {
    @GetExchange("/posts")
    List<Post> getAllPosts();

    @GetExchange("/posts/{id}")
    Post getPostById(@PathVariable Integer id);

    @PostExchange
    Post addPost(@RequestBody Post post);

    @DeleteExchange("/posts/{id}")
    void deletePostById(@PathVariable Integer id);

    @PutExchange("/posts/{id}")
    Post updatePostById(@PathVariable Integer id, @RequestBody Post post);
}
