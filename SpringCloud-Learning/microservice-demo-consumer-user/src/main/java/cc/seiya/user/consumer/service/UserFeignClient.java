package cc.seiya.user.consumer.service;

import cc.seiya.user.consumer.FeignConfiguration;
import cc.seiya.user.consumer.pojo.User;
import feign.Param;
import feign.RequestLine;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author: libo
 * @date: 2018/1/22 18:27
 */
@FeignClient(
        name = "microservice-demo-provider-user",
        // configuration = {FeignConfiguration.class},
//        fallback = UserFeignClientFallback.class
        fallbackFactory = UserFeignFallbackFactory.class
)
public interface UserFeignClient {

//    @GetMapping(value = "/user/{id}")
//    User findById(@PathVariable("id") Long id);

    @RequestLine("GET /user/{id}")
    User getById(@Param("id") Long id);

}
