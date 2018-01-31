package cc.seiya.gateway;

import cc.seiya.gateway.fileter.PreRequestLogFilter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

/**
 * zuul application
 *
 * @author: libo
 * @date: 2018/1/23 9:40
 */
@SpringBootApplication
@EnableZuulProxy
public class ZuulApplication {

    @Bean
    public PreRequestLogFilter preRequestLogFilter(){
        return new PreRequestLogFilter();
    }

    public static void main(String[] args) {
        SpringApplication.run(ZuulApplication.class, args);
    }

}
