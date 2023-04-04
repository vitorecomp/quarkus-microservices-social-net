package study.arch.socialnet.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class FollowDTO {

    Long id;
    Long me;
    Long who;
}