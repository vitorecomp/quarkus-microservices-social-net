package study.arch.socialnet.dao;

import jakarta.enterprise.context.ApplicationScoped;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import study.arch.socialnet.domain.Like;

@ApplicationScoped
public class LikeDAO implements PanacheRepository<Like>{

    public void create(Long postId, Long user) {
        Like like = new Like();
        like.setPostId(postId);
        like.setUserId(user);
        persist(like);
    }

    public Boolean isLiked(Long postId, Long user) {
        return find("postId = ?1 and userId = ?2", postId, user).firstResult() != null;
    }
}
