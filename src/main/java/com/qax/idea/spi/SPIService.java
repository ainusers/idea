package com.qax.idea.spi;

import java.io.Serializable;

public interface SPIService extends Serializable {

    void action();

    void init();

    void run();

    void stop();

    void restart();

}
