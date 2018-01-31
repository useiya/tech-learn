package cc.seiya.user.provider.repository;

import cc.seiya.user.provider.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author libo
 * @date 2018/1/19  23:15
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
