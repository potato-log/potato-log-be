package site.potatolog.potatolog.post.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import site.potatolog.potatolog.common.dto.CommonResponse;
import site.potatolog.potatolog.post.dto.PostRequest;
import site.potatolog.potatolog.post.dto.PostResponse;
import site.potatolog.potatolog.post.service.PostService;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

  private final PostService postService;

  @PostMapping
  public ResponseEntity<CommonResponse<PostResponse>> writePost(@RequestBody PostRequest postRequest) {
    Long postId = postService.writePost(postRequest);
    PostResponse response = PostResponse.fromId(postId);
    return new ResponseEntity<>(CommonResponse.created(response), HttpStatus.CREATED);
  }

  @PostMapping("/{postId}/images")
  public ResponseEntity<CommonResponse<Void>> savePostImage(@PathVariable Long postId, @RequestBody String imageUrl) {
    postService.savePostImage(postId, imageUrl);
    return new ResponseEntity<>(CommonResponse.ok(null), HttpStatus.OK);
  }

}
