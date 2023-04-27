package study.arch.socialnet.rest;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;


import study.arch.socialnet.dao.UsersDAO;
import study.arch.socialnet.domain.User;
import study.arch.socialnet.dto.UserCreateDTO;
import study.arch.socialnet.dto.UserDTO;
import study.arch.socialnet.dto.mapper.UserMapper;

@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class UserResource {
    
    UsersDAO usersDAO;
    UserMapper userMapper;
    
    @Inject
    UserResource(UsersDAO usersDAO, UserMapper userMapper){
        this.usersDAO = usersDAO;
        this.userMapper = userMapper;
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
    public UserDTO create(UserCreateDTO userCreateDTO) {
        User user = userMapper.toEntity(userCreateDTO);
        if(user.getId() != null){
            User dbUser = usersDAO.findById(user.getId());
            if(dbUser != null){
                user = dbUser;
            }else{
                user.setId(null);
            }
        }
        
        usersDAO.persist(user);
        return userMapper.toResource(user);
    }
     

    @DELETE
    @Path("/{id}")
    @Transactional
    public void delete(@PathParam(value = "id") Long id) throws NotFoundException {
        User user = usersDAO.findById(id);
        if(user == null){
            throw new NotFoundException();
        }
        usersDAO.delete(user);
    }
}