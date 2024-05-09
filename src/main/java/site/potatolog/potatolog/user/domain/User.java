package site.potatolog.potatolog.user.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import site.potatolog.potatolog.common.domain.BaseEntityWithIsDeleted;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "users")
public class User extends BaseEntityWithIsDeleted {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;

    @Column(nullable = false, length = 20)
    private String nickname;

    @Column(nullable = false, length = 100)
    private String email;

    @Column(name = "social_account_uid", nullable = false)
    private String socialAccountUid;

    @Column(name = "profile_image_url", nullable = false)
    private String profileImageUrl;

    @Column(name = "blog_name", nullable = false, length = 20)
    private String blogName;

    @Column(name = "blog_info", length = 500)
    private String blogInfo;

    @Column(name = "follower_count", nullable = false)
    private int followerCount;

    @Column(name = "following_count", nullable = false)
    private int followingCount;

}
