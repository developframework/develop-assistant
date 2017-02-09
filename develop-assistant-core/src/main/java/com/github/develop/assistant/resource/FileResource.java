package com.github.develop.assistant.resource;

import java.io.*;

/**
 * 文件资源
 */
public class FileResource implements Resource{

    private File file;

    public FileResource(File file) {
        this.file = file;
    }

    @Override
    public InputStream getInputStream() throws FileNotFoundException {
        return new FileInputStream(file);
    }

    @Override
    public OutputStream getOutputStream() throws FileNotFoundException {
        return new FileOutputStream(file);
    }
}
