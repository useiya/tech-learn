package cc.seiya.user.consumer;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: libo
 * @date: 2018/1/22 18:12
 */
@Configuration
public class RibbonConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public IRule ribbonRule(){
        return new RandomRule();
    }
}
