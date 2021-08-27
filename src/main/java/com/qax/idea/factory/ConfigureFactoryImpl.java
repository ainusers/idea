package com.qax.idea.factory;

import lombok.extern.slf4j.Slf4j;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

@Slf4j
public class ConfigureFactoryImpl implements ConfigureFactory {


    @Override
    public <T extends Configure> T factory(Class<T> tClass, String path)  {
        return instance(tClass, path);
    }

    @Override
    public <T extends Configure> T factory(Class<T> tClass, File file)  {
        return instance(tClass, file);
    }

    @Override
    public <T extends Configure> T factory(Class<T> tClass, InputStream in) {
        return instance(tClass, in);
    }


    public <T extends Configure> T instance(Class<T> tClass, Object o){
        T configure = null;
        try {
            configure = tClass.newInstance();
            if (o instanceof String) {
                configure.initConfigure((String) o);
            } else if (o instanceof File) {
                configure.initConfigure((File) o);
            } else if (o instanceof InputStream) {
                configure.initConfigure((InputStream) o);
            } else {
                log.error("错误的参数路径");
            }
        } catch (InstantiationException | IllegalAccessException | IOException e) {
            e.printStackTrace();
        }
        return configure;
    }
}

