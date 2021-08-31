package com.qax.idea.spi;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.ServiceLoader;

/**
 * @author: tianyong
 * @time: 2021/8/30 18:36
 * @description:
 * @Version: v1.0
 * @company: Qi An Xin Group.Situation 态势感知事业部
 */
@Component
public class Model implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        ServiceLoader<SPIService> loader = ServiceLoader.load(SPIService.class);
        for(SPIService service:loader){
            service.run();
        }
    }
}
