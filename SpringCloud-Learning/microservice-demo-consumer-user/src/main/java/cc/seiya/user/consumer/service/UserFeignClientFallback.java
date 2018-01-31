package cc.seiya.user.consumer.service;

import cc.seiya.user.consumer.pojo.User;
import java.math.BigDecimal;
import org.springframework.stereotype.Component;

/**
 * @author: libo
 * @date: 2018/1/23 14:01
 */
@Component(value = "userFeignClientFallback")
public class UserFeignClientFallback implements UserFeignClient {

    @Override
    public User getById(Long id) {
        User user = new User();
        user.setId(-2L);
        user.setUserName("默认用户-Feign");
        user.setAge(0);
        user.setBalance(BigDecimal.ZERO);
        user.setName("default");
        return user;
    }
}
