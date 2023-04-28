package study.arch.socialnet.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UserDTO {
    Long id;
    String name;

    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}