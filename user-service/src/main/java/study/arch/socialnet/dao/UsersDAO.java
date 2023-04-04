package study.arch.socialnet.dao;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Page;
import study.arch.socialnet.domain.User;


@ApplicationScoped
public class UsersDAO implements PanacheRepository<User> {

    public List<User> listByPage(Integer index, Integer size) {
        return findAll().page(Page.of(index, size)).list();
    }
}