package com.qax.idea.spi;

import java.io.Serializable;

/**
 * @author: tianyong
 * @time: 2021/8/30 18:36
 * @description:
 * @Version: v1.0
 * @company: QiXin Group.Situation xx事业部
 */
public interface SPIService extends Serializable {

    void action();

    void init();

    void run();

    void stop();

    void restart();

}
