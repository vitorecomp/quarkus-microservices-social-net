package study.arch.socialnet.rest;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import study.arch.socialnet.dao.FollowDAO;
import study.arch.socialnet.dao.UsersDAO;
import study.arch.socialnet.domain.Follow;
import study.arch.socialnet.domain.User;
import study.arch.socialnet.dto.FollowDTO;
import study.arch.socialnet.dto.mapper.FollowMapper;

@Path("/follow")
public class FollowersResource {

    FollowDAO followDAO;
    FollowMapper followMapper;
    UsersDAO userDao;

    @Inject
    FollowersResource(FollowDAO followDAO, FollowMapper followMapper, UsersDAO userDao) {
        this.followDAO = followDAO;
        this.followMapper = followMapper;
        this.userDao = userDao;
    }

    @GET
    @Path("/")
    public List<FollowDTO> lookFollowing(@HeaderParam(value = "X-User-Id") Long followerId) {
        return followMapper.toResourceList(followDAO.findByFollowerId(followerId));
    }

    @POST
    @Path("/{followingId}")
    @Transactional
    public FollowDTO create(@HeaderParam(value = "X-User-Id") Long followerId, @PathParam(value = "followingId") Long followingId) {
        User following = userDao.findById(followingId);
        if(following == null) throw new NotFoundException();

        User follower = userDao.findById(followerId);
        if(follower == null) throw new NotFoundException();

        Follow follow  = followDAO.follow(follower, following);
        return followMapper.toResource(follow);
    }

    @DELETE
    @Path("/{followingId}")
    @Transactional
    public void delete(@HeaderParam(value = "X-User-Id") Long followerId, @PathParam(value = "followingId") Long followingId) {
        Follow follow = followDAO.findLink(followerId, followingId);
        if(follow != null){
            followDAO.delete(follow);
        }else{
            throw new NotFoundException();
        }
    }
}