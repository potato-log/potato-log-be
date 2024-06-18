package site.potatolog.potatolog.post.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.potatolog.potatolog.post.domain.Post;
import site.potatolog.potatolog.post.domain.PostTag;
import site.potatolog.potatolog.post.domain.PostTagRepository;
import site.potatolog.potatolog.post.domain.Tag;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostTagService {

  private final PostTagRepository postTagRepository;

  @Transactional
  public void addTagsPost(Post post, List<Tag> tags) {
    for (Tag tag : tags) {
      PostTag postTag = PostTag.builder()
              .post(post)
              .tag(tag)
              .build();
      postTagRepository.save(postTag);
    }
  }
}
