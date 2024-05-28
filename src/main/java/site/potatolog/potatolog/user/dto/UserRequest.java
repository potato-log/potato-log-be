package site.potatolog.potatolog.user.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import site.potatolog.potatolog.user.domain.User;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true) // 알려지지 않은 필드 무시
public class UserRequest {

    private Integer id;
    private String name;
    private String html_url;
    private String node_id;
    private String avatar_url;

    public User toEntity() {
        return User.builder()
                .githubId(id)
                .nickname(name)
                .email(html_url)
                .socialAccountUid(node_id)
                .profileImageUrl(avatar_url)
                .build();
    }
}
