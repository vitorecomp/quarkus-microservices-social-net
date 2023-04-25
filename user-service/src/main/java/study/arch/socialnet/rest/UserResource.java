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

import study.arch.socialnet.dao.UsersDAO;
import study.arch.socialnet.domain.User;
import study.arch.socialnet.dto.UserDTO;
import study.arch.socialnet.dto.mapper.UserMapper;

@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class UserResource {
    
    UsersDAO usersDAO;
    UserMapper userMapper;
    Logger logger;
    
    @Inject
    UserResource(UsersDAO usersDAO, UserMapper userMapper, Logger logger){
        this.usersDAO = usersDAO;
        this.userMapper = userMapper;
        this.logger = logger;
    }

    @GET
    public List<UserDTO> look(@QueryParam("index") @DefaultValue("0") int index, @QueryParam("size") @DefaultValue("100") int size) {
        return userMapper.toResourceList(usersDAO.listByPage(index, size));
    }

    @GET
    @Path("/{id}")
    public UserDTO look(@PathParam("id") Long id) throws NotFoundException {
        User user = usersDAO.findById(id);
        if(user == null){
            throw new NotFoundException();
        }
        return userMapper.toResource(user);
    }

    @POST
    @Transactional
    public UserDTO create(UserDTO userDTO) {
        logger.info("Creating user");
        User user = userMapper.toEntity(userDTO);
        User dbUser = usersDAO.findById(user.getId());
        if(dbUser != null){
            user = dbUser;
        }
        usersDAO.persist(user);
        return userMapper.toResource(user);
    }
     

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam(value = "id") Long id) throws NotFoundException {
        User user = usersDAO.findById(id);
        if(user == null){
            throw new NotFoundException();
        }
        usersDAO.delete(user);
    }
}