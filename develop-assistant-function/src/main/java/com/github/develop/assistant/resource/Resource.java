package com.github.develop.assistant.resource;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 资源
 */
public interface Resource {

    InputStream getInputStream() throws IOException;

    OutputStream getOutputStream() throws  IOException;
}
