package study.arch.socialnet.rest;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import study.arch.socialnet.dao.CommentDAO;
import study.arch.socialnet.domain.Comment;
import study.arch.socialnet.dto.CommentDTO;
import study.arch.socialnet.dto.mapper.CommentMapper;

@Path("/post/{postId}/comment")
public class CommentResource {

    CommentMapper commentMapper;
    CommentDAO commentDAO;

    @Inject
    CommentResource(CommentMapper commentMapper, CommentDAO commentDAO) {
        this.commentMapper = commentMapper;
        this.commentDAO = commentDAO;
    }


    @GET
    public List<CommentDTO> list(@PathParam("postId") Long postId) {
        return commentMapper.toResourceList(commentDAO.listByPost(postId));
    }

    @GET
    @Path("{id}")
    public CommentDTO get(@PathParam("postId") Long postId, @PathParam("id") Long id) {
        Comment comment = commentDAO.findByPostAndId(postId, id);
        return commentMapper.toResource(comment);
    }


    @POST
    public CommentDTO create(CommentDTO commentDTO) {
        Comment comment = commentMapper.toEntity(commentDTO);
        commentDAO.persist(comment);
        return commentMapper.toResource(comment);
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("postId") Long postId, @PathParam("id") Long id) {
        Comment comment = commentDAO.findByPostAndId(postId, id);
        if(comment == null){
            throw new NotFoundException();
        }
        
        commentDAO.delete(comment);
    }
}
