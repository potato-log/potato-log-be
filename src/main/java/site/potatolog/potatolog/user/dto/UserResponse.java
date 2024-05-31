package site.potatolog.potatolog.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import site.potatolog.potatolog.user.domain.User;

@Getter
@NoArgsConstructor
@ToString
public class UserResponse {
    private String nickname;

    private String email;

    private String socialAccountUid;

    private String profileImageUrl;

    private String blogName;

    public UserResponse(User user) {
        this.nickname = user.getNickname();
        this.email = user.getEmail();
        this.socialAccountUid = user.getSocialAccountUid();
        this.profileImageUrl = user.getProfileImageUrl();
        this.blogName = user.getBlogName();
    }
}
