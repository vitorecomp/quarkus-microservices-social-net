package study.arch.socialnet.dao;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import study.arch.socialnet.domain.Follow;
import study.arch.socialnet.domain.User;

@ApplicationScoped
public class FollowDAO implements PanacheRepository<Follow> {

    public List<Follow> findByFollowerId(Long followerId) {
        return list("follower.id", followerId);
    }

    public Follow follow(User follower, User following) {
        Follow follow = new Follow();
        follow.setFollower(follower);
        follow.setFollowing(following);
        persist(follow);
        return follow;
    }

    public Follow findLink(Long followerId, Long followingId) {
        return find("follower.id = ?1 and following.id = ?2", followerId, followingId).firstResult();
    }
    
}
