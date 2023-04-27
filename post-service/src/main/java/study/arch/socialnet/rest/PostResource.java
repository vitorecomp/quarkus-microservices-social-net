package study.arch.socialnet.rest;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;


import study.arch.socialnet.dao.PostsDAO;
import study.arch.socialnet.domain.Post;
import study.arch.socialnet.dto.PostCreateDTO;
import study.arch.socialnet.dto.PostDTO;
import study.arch.socialnet.dto.mapper.PostMapper;

@Path("/post")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class PostResource {
    PostsDAO postsDAO;
    PostMapper postMapper;
    
    @Inject
    PostResource(PostsDAO postsDAO, PostMapper postMapper){
        this.postsDAO = postsDAO;
        this.postMapper = postMapper;
    }

    @GET
    public List<PostDTO> look(@QueryParam("index") @DefaultValue("0") int index, @QueryParam("size") @DefaultValue("100") int size) {
        return postMapper.toResourceList(postsDAO.listByPage(index, size));
    }

    @GET
    @Path("/{id}")
    public PostDTO look(@PathParam("id") Long id) throws NotFoundException {
        Post post = postsDAO.findById(id);
        if(post == null){
            throw new NotFoundException();
        }
        return postMapper.toResource(post);
    }

    @POST
    @Transactional
    public PostDTO create(@HeaderParam("X-User-Id") Long userId, PostCreateDTO postCreateDTO) {        
        Post post = postMapper.toEntity(postCreateDTO);
        post.setUserId(userId);

        postsDAO.persist(post);
        return postMapper.toResource(post);
    }
     

    @DELETE
    @Path("/{id}")
    @Transactional
    public void delete(@PathParam("id") Long id) throws NotFoundException {
        Post post = postsDAO.findById(id);
        if(post == null){
            throw new NotFoundException();
        }
        postsDAO.delete(post);
    }
}