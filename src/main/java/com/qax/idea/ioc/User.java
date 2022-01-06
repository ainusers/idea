package com.qax.idea.ioc;

import lombok.Data;
import org.springframework.stereotype.Component;
import java.io.Serializable;

/**
 * @author: tianyong
 * @time: 2021/8/30 18:36
 * @description:
 * @Version: v1.0
 * @company: QiXin Group.Situation xx事业部
 */
@Data
@Component("User")
public class User implements Serializable {

    private String name;
    private Integer age;

}
