package com.qax.idea.factory;

import java.io.File;
import java.io.InputStream;

/**
 * @author: tianyong
 * @time: 2021/8/30 18:36
 * @description:
 * @Version: v1.0
 * @company: QiXin Group.Situation xx事业部
 */
public interface ConfigureFactory {


    <T extends Configure> T factory(Class<T> tClass, String path) ;


    <T extends Configure> T factory(Class<T> tClass, File file) ;


    <T extends Configure> T factory(Class<T> tClass, InputStream in) ;

}
