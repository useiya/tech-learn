package cc.seiya.user.consumer;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Configuration;

/**
 * @author: libo
 * @date: 2018/1/22 18:13
 */
@Configuration
@RibbonClient(name = "microsevice-provider-user",configuration = {RibbonConfiguration.class})
public class ProviderUserConfiguration {

}
