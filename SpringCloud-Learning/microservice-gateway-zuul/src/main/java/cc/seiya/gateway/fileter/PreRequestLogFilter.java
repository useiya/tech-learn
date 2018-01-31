package cc.seiya.gateway.fileter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;


/**
 * @author libo
 * @date 2018/1/23  23:11
 */
public class PreRequestLogFilter extends ZuulFilter {
    @Override
    public String filterType() {
        // pre  请求被路由到微服务之前调用,应用场景: 身份验证、在集群中选择请求的服务、记录调试信息
        // route    请求被路由到微服务
        // post     求被路由到微服务以后执行
        // error    在其它阶段发生错误时执行
        // static   直接响应，不会将请求路由到微服务
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext rctx = RequestContext.getCurrentContext();
        HttpServletRequest httpServletRequest = rctx.getRequest();
        LOGGER.info("send {} request to {}",httpServletRequest.getMethod(),httpServletRequest.getRequestURL());
        return null;
    }

    private final Logger LOGGER = LoggerFactory.getLogger(PreRequestLogFilter.class);
}
