package site.potatolog.potatolog.post.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.potatolog.potatolog.post.domain.*;
import site.potatolog.potatolog.post.dto.PostRequest;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

  private final PostRepository postRepository;
  private final PostImageRepository postImageRepository;
  private final TagService tagService;
  private final PostTagService postTagService;

  @Transactional
  public Long writePost(PostRequest postRequest) {
    List<Tag> tags = tagService.createTags(postRequest.getTagContents());

    Post post = postRequest.toEntity(tags);
    post = postRepository.save(post);

    postTagService.addTagsPost(post, tags);

    if (!postRequest.isTemp() && postRequest.getImageUrl() != null && !postRequest.getImageUrl().isEmpty()) {
      PostImage postImage = PostImage.builder()
              .imageUrl(postRequest.getImageUrl())
              .post(post)
              .build();

      post.addPostImage(postImage);
      postImageRepository.save(postImage);
    }

    return post.getId();
  }

  @Transactional
  public void savePostImage(Long postId, String imageUrl) {
    Post post = postRepository.findById(postId)
            .orElseThrow(() -> new IllegalArgumentException("Invalid postId: " + postId));

    PostImage postImage = PostImage.builder()
            .imageUrl(imageUrl)
            .post(post)
            .build();

    post.addPostImage(postImage);
    postImageRepository.save(postImage);
  }

}
