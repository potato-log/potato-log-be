package site.potatolog.potatolog.post.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import site.potatolog.potatolog.post.domain.Post;
import site.potatolog.potatolog.post.domain.PostImage;
import site.potatolog.potatolog.post.domain.Tag;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)

public class PostRequest {

    private String title;
    private List<String> tagContent;
    private String imageUrl;
    private String postContent;
    private boolean isTemp;

    public List<String> getTagContents() {
        return tagContent != null ? tagContent : Collections.emptyList();
    }

    public Post toEntity(List<Tag> tags) {
        List<PostImage> postImages = new ArrayList<>();

        if (this.imageUrl != null) {
            PostImage postImage = PostImage.builder()
                    .imageUrl(this.imageUrl)
                    .build();
            postImages.add(postImage);
        }

        Post post = Post.builder()
                .title(this.title)
                .content(this.postContent)
                .postImages(postImages)
                .isTemp(this.isTemp)
                .build();

        for (Tag tag : tags) {
            post.addTag(tag);
        }

        return post;
    }
}
