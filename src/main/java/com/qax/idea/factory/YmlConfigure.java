package com.qax.idea.factory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class YmlConfigure implements Configure{


    @Override
    public void initConfigure(String path) throws IOException {

    }

    @Override
    public void initConfigure(File file) throws IOException {

    }

    @Override
    public void initConfigure(InputStream inputStream) throws IOException {

    }

    public String get(){
        return "YmlConfigure test success";
    }
}
