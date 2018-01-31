package cc.seiya.user.consumer;

import feign.Contract;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author libo
 * @date 2018/1/22  22:04
 */
@Configuration
public class FeignConfiguration {

    /**
     * 将默认契约变更为feign,可使用feign的注解
     * @return
     */
    @Bean
    public Contract.Default feignContract(){
        return   new feign.Contract.Default();
    }
}
