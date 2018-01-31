package cc.seiya.user.provider.service;

import cc.seiya.user.provider.entity.User;
import cc.seiya.user.provider.repository.UserRepository;

import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author libo
 * @date 2018/1/25  23:06
 */
@Service
public class UserService {

    @Autowired
    private UserService self;

    public User findById(Long id){
        // 直接调用时aop不会被增强
        // this是当前对象(UserService)而不是代理对象,只有为代理对象时才会被aop增加
        System.out.println(this.getClass());
        this.innerMethod();
        System.out.println(self.getClass());
        Object proxy = AopContext.currentProxy();
        System.out.println(proxy.getClass());
        return userRepository.findOne(id);
    }

    public void innerMethod(){
        System.out.println("execute innerMehtod ...");
    }

    @Autowired
    private UserRepository userRepository;
}
