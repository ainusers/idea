package com.qax.idea.ioc;

import lombok.Data;
import org.springframework.stereotype.Component;
import java.io.Serializable;

/**
 * @author: tianyong
 * @time: 2021/8/30 18:36
 * @description:
 * @Version: v1.0
 * @company: Qi An Xin Group.Situation 态势感知事业部
 */
@Data
@Component("User")
public class User implements Serializable {

    private String name;
    private Integer age;

}
