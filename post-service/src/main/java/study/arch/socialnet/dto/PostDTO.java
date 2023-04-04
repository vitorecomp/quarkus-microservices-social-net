package study.arch.socialnet.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class PostDTO {
    Long id;
    Long user;
    String text;
}
