package com.qax.idea;

import com.qax.idea.chainInvoke.BaseStream;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class IdeaApplicationTests {

    @Test
    void contextLoads() {
        new BaseStream().getInstance()
          .dataHandle()
          .print();
    }

}
