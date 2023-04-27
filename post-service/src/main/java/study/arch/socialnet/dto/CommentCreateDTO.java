package study.arch.socialnet.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CommentCreateDTO {
    String version; //circumvent a bug in the current version of jackson

    String content;
}
