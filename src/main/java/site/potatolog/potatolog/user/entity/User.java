package site.potatolog.potatolog.user.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import site.potatolog.potatolog.common.entity.BaseEntityWithIsDeleted;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "users")
public class User extends BaseEntityWithIsDeleted {

    @Id
    @GeneratedValue
     private Long id;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String email;

    @Column(name = "social_account_uid", nullable = false)
    private String socialAccountUid;

    @Column(name = "profile_image_url", nullable = false)
    private String profileImageUrl;

    @Column(name = "blog_name", nullable = false)
    private String blogName;

    @Column(name = "blog_info")
    private String blogInfo;

    @Column(name = "follower_count")
    private int followerCount;

    @Column(name = "following_count")
    private int followingCount;

}
