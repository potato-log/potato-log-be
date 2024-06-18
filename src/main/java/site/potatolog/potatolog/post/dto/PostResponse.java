package site.potatolog.potatolog.post.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PostResponse {
    private Long id;

    public static PostResponse fromId(Long id) {
        return new PostResponse(id);
    }
}
