package study.arch.socialnet.dao;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import study.arch.socialnet.domain.Comment;

@ApplicationScoped
public class CommentDAO implements PanacheRepository<Comment>{

    public List<Comment> listByPost(Long postId) {
        return list("post.id", postId);
    }

    public Comment findByPostAndId(Long postId, Long id) {
        return find("post.id = ?1 and id = ?2", postId, id).firstResult();
    }
    
}
