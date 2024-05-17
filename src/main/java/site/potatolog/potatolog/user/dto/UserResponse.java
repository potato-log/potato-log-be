package site.potatolog.potatolog.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import site.potatolog.potatolog.user.domain.User;

import java.util.Map;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private String nickname;

    private String email;

    private String socialAccountUid;

    private String profileImageUrl;

    public UserResponse(User user) {
        this.nickname = user.getNickname();
        this.email = user.getEmail();
        this.socialAccountUid = user.getSocialAccountUid();
        this.profileImageUrl = user.getProfileImageUrl();
    }
}
