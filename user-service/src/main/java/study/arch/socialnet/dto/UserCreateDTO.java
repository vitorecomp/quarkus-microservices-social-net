package study.arch.socialnet.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UserCreateDTO {
    Long id;
    String name;
}