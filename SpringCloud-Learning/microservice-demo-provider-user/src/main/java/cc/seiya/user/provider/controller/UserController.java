package cc.seiya.user.provider.controller;

import cc.seiya.user.provider.entity.User;
import cc.seiya.user.provider.repository.UserRepository;
import cc.seiya.user.provider.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author libo
 * @date 2018/1/19  23:19
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("{id}")
    public User findById(@PathVariable Long id) {
        User user =  userService.findById(id);
        return user;
    }
}
