package cc.seiya.user.consumer.service;

import cc.seiya.user.consumer.pojo.User;
import feign.hystrix.FallbackFactory;
import java.math.BigDecimal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author: libo
 * @date: 2018/1/23 15:37
 */
@Component("feignFallbackFactory")
public class UserFeignFallbackFactory implements FallbackFactory<UserFeignClient> {

    private final Logger LOGGER = LoggerFactory.getLogger(UserFeignFallbackFactory.class);

    @Override
    public UserFeignClient create(Throwable throwable) {
        LOGGER.info("fallback reason was :",throwable);
        return (p1) -> {
            User user = new User();
            user.setId(-2L);
            user.setUserName("默认用户-UserFeignFallbackFactory");
            user.setAge(0);
            user.setBalance(BigDecimal.TEN);
            user.setName("fallbackFactory");
            return user;
        };
//        return new UserFeignClient() {
//            @Override
//            public User getById(Long id) {
//                User user = new User();
//                user.setId(-2L);
//                user.setUserName("默认用户-UserFeignFallbackFactory");
//                user.setAge(0);
//                user.setBalance(BigDecimal.TEN);
//                user.setName("fallbackFactory");
//                return user;
//            }
//        };
    }
}
