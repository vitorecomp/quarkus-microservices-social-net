package study.arch.socialnet.rest;

import java.util.List;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

import study.arch.socialnet.dao.CommentDAO;
import study.arch.socialnet.dao.PostsDAO;
import study.arch.socialnet.domain.Comment;
import study.arch.socialnet.domain.Post;
import study.arch.socialnet.dto.CommentCreateDTO;
import study.arch.socialnet.dto.CommentDTO;
import study.arch.socialnet.dto.mapper.CommentMapper;

@Path("/post/{postId}/comments")
public class CommentResource {

    CommentMapper commentMapper;
    CommentDAO commentDAO;
    PostsDAO postDAO;

    @Inject
    CommentResource(CommentMapper commentMapper, CommentDAO commentDAO, PostsDAO postDAO) {
        this.commentMapper = commentMapper;
        this.commentDAO = commentDAO;
        this.postDAO = postDAO;
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
    @Transactional
    public CommentDTO create(@HeaderParam("x-user-id") Long userId, @PathParam("postId") Long postId, CommentCreateDTO commentcreateDTO) {
        Post post = postDAO.findById(postId);
        if(post == null){
            throw new NotFoundException();
        }

        Comment comment = commentMapper.toEntity(commentcreateDTO);
        comment.setPost(post);
        comment.setUserId(userId);
        
        commentDAO.persist(comment);
        return commentMapper.toResource(comment);
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public void delete(@PathParam("postId") Long postId, @PathParam("id") Long id) {
        Comment comment = commentDAO.findByPostAndId(postId, id);
        if(comment == null){
            throw new NotFoundException();
        }
        
        commentDAO.delete(comment);
    }
}
