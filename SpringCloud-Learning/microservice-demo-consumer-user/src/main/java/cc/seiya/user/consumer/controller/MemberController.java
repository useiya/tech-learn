package cc.seiya.user.consumer.controller;

import cc.seiya.user.consumer.pojo.User;
import cc.seiya.user.consumer.service.UserFeignClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.netflix.eureka.EurekaDiscoveryClient.EurekaServiceInstance;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author libo
 * @date 2018/1/20  0:04
 */
@RestController
@RequestMapping("member")
public class MemberController {

    private final Logger LOGGER = LoggerFactory.getLogger(MemberController.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private UserFeignClient userFeignClient;

    @GetMapping("{id}")
    @HystrixCommand(
            fallbackMethod = "findByIdFallback",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000"),
                    @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "1000")},
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize", value = "4"),
                    @HystrixProperty(name = "maxQueueSize", value = "100")}
    )
    public User info(@PathVariable Long id) {
//        URI providerUri = this.discoveryClient.getInstances("MICROSERVICE-DEMO-PROVIDER-USER")
//                .get(0).getUri();
        // 若使用ribbon进行负载 根据IP 地址找不到对应的provider
        EurekaServiceInstance serviceInstance = (EurekaServiceInstance) this.discoveryClient.getInstances("MICROSERVICE-DEMO-PROVIDER-USER")
                .get(0);
        String host = serviceInstance.getInstanceInfo().getAppName();
        User user = restTemplate.getForObject("http://"+host + "/user/" + id, User.class);
        return user;
    }

    @GetMapping("provider-info")
    public List<ServiceInstance> showproviderInfo() {
        List<ServiceInstance> instances = this.discoveryClient
                .getInstances("microservice-demo-provider-user");
        return instances;
    }

    @GetMapping("provider-instance")
    public void providerInstance() {
        ServiceInstance serviceInstance = this.loadBalancerClient
                .choose("microservice-demo-provider-user");
        LOGGER.info("{}:{}:{}", serviceInstance.getServiceId(), serviceInstance.getHost(),
                serviceInstance.getPort());
    }

    @GetMapping("feign/{id}")
    public User findByIdByFeign(@PathVariable Long id) {
//        User user = this.userFeignClient.findById(id);

//        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        if (principal instanceof UserDetails) {
//            UserDetails userDetails = (UserDetails) principal;
//            Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
//            authorities.stream().forEach(s -> {
//                LOGGER.info("当前用户:{},角色是:{}", userDetails.getUsername(), s.getAuthority());
//            });
//        }

        User user = this.userFeignClient.getById(id);
        return user;
    }


    private User findByIdFallback(Long id) {
        User user = new User();
        user.setId(-1L);
        user.setUserName("默认用户");
        user.setAge(0);
        return user;
    }

}
