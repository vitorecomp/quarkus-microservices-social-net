package study.arch.socialnet.dto.mapper;

import org.mapstruct.Mapper;

import java.util.List;

import study.arch.socialnet.domain.Comment;
import study.arch.socialnet.dto.CommentDTO;

@Mapper(componentModel = "cdi")
public interface CommentMapper {
    CommentDTO toResource(Comment comment);

    List<CommentDTO> toResourceList(List<Comment> comment);

    Comment toEntity(CommentDTO commentDTO);
}
