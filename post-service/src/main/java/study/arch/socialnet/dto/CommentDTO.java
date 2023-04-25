package study.arch.socialnet.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CommentDTO {
    Long id;
    String text;
    Long userId;

    PostDTO post;
}
