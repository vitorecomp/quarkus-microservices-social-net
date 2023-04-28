package study.arch.socialnet.rest;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

import study.arch.socialnet.dao.LikeDAO;
import study.arch.socialnet.dao.PostsDAO;
import study.arch.socialnet.dto.PostDTO;
import study.arch.socialnet.dto.mapper.PostMapper;

@Path("/like")
public class LikeResource {

    LikeDAO likeDAO;
    PostsDAO postsDAO;
    PostMapper postMapper;

    @Inject
    LikeResource(LikeDAO likeDAO, PostsDAO postsDAO, PostMapper postMapper) {
        this.likeDAO = likeDAO;
        this.postsDAO = postsDAO;
        this.postMapper = postMapper;
    }
    
    @POST
    @Transactional
    @Path("/{postId}")
    public PostDTO like(@PathParam("postId") Long postId, @HeaderParam("X-User-Id") Long userId) {
        if (!postsDAO.exists(postId)) {
            throw new NotFoundException();
        }

        if(!likeDAO.isLiked(postId, userId)){
            likeDAO.create(postId, userId);
            postsDAO.incrementLike(postId);
        }
        
        return postMapper.toResource(postsDAO.findById(postId));
    }
}
