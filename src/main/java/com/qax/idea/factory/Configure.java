package com.qax.idea.factory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author: tianyong
 * @time: 2021/8/30 18:36
 * @description:
 * @Version: v1.0
 * @company: Qi An Xin Group.Situation 态势感知事业部
 */
public interface Configure {


    void initConfigure(String path) throws IOException;


    void initConfigure(File file) throws IOException;


    void initConfigure(InputStream inputStream) throws IOException;

}
