package site.potatolog.potatolog.post.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.potatolog.potatolog.post.domain.Post;
import site.potatolog.potatolog.post.domain.PostRepository;
import site.potatolog.potatolog.post.domain.Tag;
import site.potatolog.potatolog.post.domain.TagRepository;
import site.potatolog.potatolog.post.dto.PostRequest;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final TagRepository tagRepository;

    @Transactional
    public void writePost(PostRequest postRequest) {
        Post post = postRequest.toEntity();

        Tag tag = Tag.builder()
                .content(postRequest.getTagContent())
                .build();
        tagRepository.save(tag);

        post.addTag(tag);

        postRepository.save(post);
    }

}
