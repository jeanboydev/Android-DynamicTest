package com.jeanboy.app.dynamictest.classloader;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import dalvik.system.DexClassLoader;

public class CustomClassLoader extends DexClassLoader {
    public CustomClassLoader(String dexPath, String optimizedDirectory, String librarySearchPath, ClassLoader parent) {
        super(dexPath, optimizedDirectory, librarySearchPath, parent);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] classData = getClassData(name);
        if (classData != null) {
            return defineClass(name, classData, 0, classData.length);
        } else {
            throw new ClassNotFoundException();
        }
    }

    private byte[] getClassData(String name) {
        try {
            InputStream inputStream = new FileInputStream(name);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            int bufferSize = 4096;
            byte[] buffer = new byte[bufferSize];
            int byteNumRead = -1;
            while ((byteNumRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, byteNumRead);
            }
            return outputStream.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
