package cc.seiya.user.consumer.pojo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 用户模块
 * @author libo
 * @date 2018/1/19  23:09
 */
@Data
public class User implements Serializable{

    private Long id;

    private String userName;

    private String name;

    private Integer age;

    private BigDecimal balance;
}
