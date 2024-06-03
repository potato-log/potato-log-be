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

    private String address;

    private String socialAccountUid;

    private String profileImageUrl;

    private String blogName;

    public UserResponse(User user) {
        this.nickname = user.getNickname();
        this.address = user.getAddress();
        this.socialAccountUid = user.getSocialAccountUid();
        this.profileImageUrl = user.getProfileImageUrl();
        this.blogName = user.getBlogName();
    }
}
