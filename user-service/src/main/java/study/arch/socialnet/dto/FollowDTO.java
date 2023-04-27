package study.arch.socialnet.dto;

import lombok.Builder;
import lombok.Value;

@Value @Builder
public class FollowDTO {

    Long id;
    
    UserCreateDTO follower;
    UserCreateDTO following;
}