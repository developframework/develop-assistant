package com.github.develop.assistant;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

/**
 * 加载器
 */
public class FunctionLoader {


    private String dir;
    private String[] jarPaths;

    public FunctionLoader(String dir, String[] jarPaths) {
        this.dir = dir;
        this.jarPaths = jarPaths;
    }

    public List<HotKeyFunction> load() {
        final List<HotKeyFunction> hotKeyFunctions = new ArrayList<>();
        for (String jarPath : jarPaths) {
            URL url;
            try {
                url = new URL("file:" + dir + File.separator + jarPath);
            } catch (MalformedURLException e) {
                continue;
            }
            final URLClassLoader urlClassLoader = new URLClassLoader(new URL[]{url}, Thread.currentThread().getContextClassLoader());
            InputStream is = urlClassLoader.getResourceAsStream("function.handlers");
            if (is == null) {
                throw new RuntimeException("Not found function.handlers in jar.");
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            br.lines().forEach(line -> {
                try {
                    Class<?> functionClass = urlClassLoader.loadClass(line);
                    if (HotKeyFunction.class.isAssignableFrom(functionClass)) {
                        hotKeyFunctions.add((HotKeyFunction) functionClass.newInstance());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return hotKeyFunctions;
    }
}
