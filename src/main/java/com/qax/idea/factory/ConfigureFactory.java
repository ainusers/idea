package com.qax.idea.factory;

import java.io.File;
import java.io.InputStream;


public interface ConfigureFactory {


    <T extends Configure> T factory(Class<T> tClass, String path) ;


    <T extends Configure> T factory(Class<T> tClass, File file) ;


    <T extends Configure> T factory(Class<T> tClass, InputStream in) ;

}
