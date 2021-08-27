package com.qax.idea.factory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public interface Configure {


    void initConfigure(String path) throws IOException;


    void initConfigure(File file) throws IOException;


    void initConfigure(InputStream inputStream) throws IOException;

}
