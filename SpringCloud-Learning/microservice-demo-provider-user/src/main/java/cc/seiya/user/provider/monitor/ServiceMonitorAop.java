package cc.seiya.user.provider.monitor;

import org.apache.commons.lang.ArrayUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * 服务调用监控Aop
 *
 * @author: libo
 * @date: 2018/1/25 16:14
 */
@Aspect
@Component("serviceMonitorAop")
public class ServiceMonitorAop {

    private final Logger LOGGER = LoggerFactory.getLogger(ServiceMonitorAop.class);
    private final String MONITOR_PKG = "execution(public * cc.seiya.user.provider.service..*.*(..))";


    /**
     * 定义切点
     */
    @Pointcut(value = MONITOR_PKG)
    public void pointcut(){

    }
    /**
     * 定义Before增强处理
     *
     * @param jp
     */
//    @Before(value = "pointcut()")
    public void before(JoinPoint jp) {
        LOGGER.warn("Before增强处理：被织入增强处理的目标方法为：{}", jp.getSignature().toString());
        LOGGER.warn("Before增强：目标方法的参数为：" + (jp.getArgs()));
        LOGGER.warn("Before增强：被织入增强处理的目标对象为：" + jp.getTarget());
    }

    /**
     * 定义Around增强处理
     **/
    @Around(value = "pointcut()")
    public Object around(ProceedingJoinPoint jp) throws Throwable {
        Signature signature = jp.getSignature();
        String methodInfo = signature.toString();
        StringBuilder buff = new StringBuilder(32);
        // 访问执行目标方法的参数
        Object[] args = jp.getArgs();
        if (!ArrayUtils.isEmpty(args)) {
            int i = 0;
            int len = args.length;
            for (; i < len; i++) {
                buff.append("第[").append(i).append("]个参数值为[").append(args[i]).append("],");
            }
            len = buff.length();
            buff.deleteCharAt(len - 1);
        }
        // 当执行目标方法的参数存在
        LOGGER.warn("Around增强：执行目标方法[{}],参数[{}]，模拟开始事物...", methodInfo, buff.toString());
        long start = System.currentTimeMillis();
        // 执行目标方法，并保存目标方法执行后的返回值
        Object rvt = jp.proceed(args);
        long end = System.currentTimeMillis();
        LOGGER.warn("Around增强：执行目标方法[{}],时长为:[{}]ms，模拟结束事物...", methodInfo, (end - start));
        return rvt;
    }

    /**
     * 定义AfterReturning增强处理
     **/
//    @AfterReturning(pointcut = "pointcut()", returning = "rvt")
    public void afterReturning(JoinPoint jPoint, Object rvt) {
        LOGGER.warn("AfterReturning增强：获取目标方法返回值：{}", rvt);
        LOGGER.warn("AfterReturning增强：模拟日志记录功能...");
        // 返回被织入增强处理的目标方法
        LOGGER.warn("AfterReturning增强：被织入增强处理的目标为：{}", jPoint.getSignature().toString());
        // 访问执行目标方法的参数
        LOGGER.warn("AfterReturning增强：目标方法的参数为：{}", Arrays.toString(jPoint.getArgs()));
        // 访问被增强处理的目标对象
        LOGGER.warn("AfterReturning增强：被织入增强处理的目标对象为：{}", jPoint.getTarget());
    }

    /**
     * 定义After增强处理
     *
     * @param jp
     */
//    @After(value = "pointcut()")
    public void after(JoinPoint jp) {
        LOGGER.warn("After增强：模拟方法结束后的释放资源...");
        // 被返回织入增强处理的目标方法
        LOGGER.warn("After增强：被织入增强处理的目标方法为：{}", jp.getSignature().toString());

        // 访问执行目标方法的参数
        LOGGER.warn("After增强：目标方法的参数为：{}", Arrays.toString(jp.getArgs()));

        // 访问被增强处理的目标对象
        LOGGER.warn("After增强：被织入增强处理的目标对象为：{}", jp.getTarget());
    }


}
