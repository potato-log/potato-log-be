package site.potatolog.potatolog.post.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import site.potatolog.potatolog.common.domain.BaseEntityWithIsDeleted;
import site.potatolog.potatolog.user.domain.User;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "posts")
public class Post extends BaseEntityWithIsDeleted {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(name = "view_count", nullable = false)
    private int viewCount;

    @Column(name = "is_temp", nullable = false)
    private boolean isTemp;

    @Column(name = "like_count", nullable = false)
    private int likeCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PostImage> postImages = new ArrayList<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PostTag> postTags = new ArrayList<>();

    public void addTag(Tag tag) {
        PostTag postTag = new PostTag(this, tag);
        postTags.add(postTag);
        tag.getPostTags().add(postTag);
    }

    @Builder
    public Post(Long id, String title, String content, int viewCount, boolean isTemp, int likeCount, User user, List<PostImage> postImages) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.viewCount = viewCount;
        this.isTemp = isTemp;
        this.likeCount = likeCount;
        this.user = user;
        this.postImages = postImages;
        this.postTags = new ArrayList<>();
    }
}
