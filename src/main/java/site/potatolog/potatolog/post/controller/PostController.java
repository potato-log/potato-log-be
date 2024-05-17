package site.potatolog.potatolog.post.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.potatolog.potatolog.post.dto.PostRequest;
import site.potatolog.potatolog.post.service.PostService;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<String> writePost(@RequestBody PostRequest postRequest) {
        postService.writePost(postRequest);
        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }

}
