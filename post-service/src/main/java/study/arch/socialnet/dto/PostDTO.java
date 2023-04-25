package study.arch.socialnet.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class PostDTO {
    Long id;
    Long userId;
    String text;
    Long likes;

    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
