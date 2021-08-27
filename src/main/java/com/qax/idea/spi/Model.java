package com.qax.idea.spi;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.ServiceLoader;

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
