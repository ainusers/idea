package com.qax.idea.ioc;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Data
@Component("WebShellTrojanEntity")
public class User implements Serializable {

    private String name;
    private Integer age;

}
