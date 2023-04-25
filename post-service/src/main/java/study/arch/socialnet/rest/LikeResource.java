package study.arch.socialnet.rest;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

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
    @Path("/{postId}}")
    public PostDTO like(@PathParam("postId") Long postId, @HeaderParam("X-User-Id") Long userId) {
        if (postsDAO.exists(postId)) {
            throw new NotFoundException();
        }

        if(!likeDAO.isLiked(postId, userId)){
            likeDAO.create(postId, userId);
            postsDAO.incrementLike(postId);
        }
        
        return postMapper.toResource(postsDAO.findById(postId));
    }
}
