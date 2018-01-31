package cc.seiya.user.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author libo
 * @date 2018/1/19  23:30
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableHystrix
@EnableAspectJAutoProxy(exposeProxy = true) // 使用 AopContext.currentProxy() 生效
public class ProvideUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProvideUserApplication.class,args);
    }
}
