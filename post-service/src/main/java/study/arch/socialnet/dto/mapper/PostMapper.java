package study.arch.socialnet.dto.mapper;

import org.mapstruct.Mapper;

import java.util.List;

import study.arch.socialnet.domain.Post;
import study.arch.socialnet.dto.PostDTO;

@Mapper(componentModel = "cdi")
public interface PostMapper {
    PostDTO toResource(Post post);

    List<PostDTO> toResourceList(List<Post> posts);

    Post toEntity(PostDTO postDTO);
}
