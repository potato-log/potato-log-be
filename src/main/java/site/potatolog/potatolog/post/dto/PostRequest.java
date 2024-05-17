package site.potatolog.potatolog.post.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import site.potatolog.potatolog.post.domain.Post;
import site.potatolog.potatolog.post.domain.PostImage;
import site.potatolog.potatolog.post.domain.Tag;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostRequest {

    private String title;
    private String tagContent;
    private String imageUrl;
    private String postContent;
    private boolean isTemp;

    public Post toEntity() {
        Tag tag = Tag.builder()
                .content(this.tagContent)
                .build();

        PostImage postImage = PostImage.builder()
                .imageUrl(this.imageUrl)
                .build();

        List<PostImage> postImages = new ArrayList<>();
        postImages.add(postImage);

        return Post.builder()
                .title(this.title)
                .content(this.postContent)
                .postImages(postImages)
                .isTemp(this.isTemp)
                .build();
    }
}
