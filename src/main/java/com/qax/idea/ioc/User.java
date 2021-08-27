package com.qax.idea.ioc;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Data
@Component("User")
public class User implements Serializable {

    private String name;
    private Integer age;

}
