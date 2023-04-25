package study.arch.socialnet.rest;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.jboss.logging.Logger;

import study.arch.socialnet.dao.PostsDAO;
import study.arch.socialnet.domain.Post;
import study.arch.socialnet.dto.PostDTO;
import study.arch.socialnet.dto.mapper.PostMapper;

@Path("/post")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class PostResource {
    PostsDAO postsDAO;
    PostMapper postMapper;
    Logger logger;
    
    @Inject
    PostResource(PostsDAO postsDAO, PostMapper postMapper, Logger logger){
        this.postsDAO = postsDAO;
        this.postMapper = postMapper;
        this.logger = logger;
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
    public PostDTO create(PostDTO PostDTO) {
        logger.info("Creating user");
        Post post = postMapper.toEntity(PostDTO);
        postsDAO.persist(post);
        return postMapper.toResource(post);
    }
     

    @DELETE
    @Path("/{id}")
    @Transactional
    public void delete(@PathParam(value = "id") Long id) throws NotFoundException {
        Post post = postsDAO.findById(id);
        if(post == null){
            throw new NotFoundException();
        }
        postsDAO.delete(post);
    }
}