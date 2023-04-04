package study.arch.socialnet.dto.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import study.arch.socialnet.domain.Follow;
import study.arch.socialnet.dto.FollowDTO;

@Mapper(componentModel = "cdi")
public interface FollowMapper {

    FollowDTO toResource(Follow follow);

    List<FollowDTO> toResourceList(List<Follow> follows);
}