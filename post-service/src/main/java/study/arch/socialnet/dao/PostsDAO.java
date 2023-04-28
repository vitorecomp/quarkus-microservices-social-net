package study.arch.socialnet.dao;

import jakarta.enterprise.context.ApplicationScoped;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Page;
import study.arch.socialnet.domain.Post;
import java.util.List;


@ApplicationScoped
public class PostsDAO implements PanacheRepository<Post> {
    public List<Post> listByPage(Integer index, Integer size) {
        return find("order by id asc").page(Page.of(index, size)).list();
    }

    public void incrementLike(Long postId) {
        Post post = findById(postId);
        post.setLikes(post.getLikes() + 1);
        persist(post);
    }

    public boolean exists(Long postId) {
        return findById(postId) != null;
    }

    public List<Post> listByLastId(Long lastId, int index, int size) {
        return find("id > ?1 order by id asc", lastId).page(Page.of(index, size)).list();
    }
    
}
