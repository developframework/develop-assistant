package com.github.develop.assistant;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Administrator on 2017/1/25.
 */
public class FunctionLoader {


    private String dir;
    private String[] jarPaths;

    public FunctionLoader(String dir, String[] jarPaths) {
        this.dir = dir;
        this.jarPaths = jarPaths;
    }

    public Set<HotKeyFunction> load() {
        final Set<HotKeyFunction> hotKeyFunctionSet = new HashSet<>();
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
                System.out.println(line);
                try {
                    Class<?> functionClass = urlClassLoader.loadClass(line);
                    if (HotKeyFunction.class.isAssignableFrom(functionClass)) {
                        hotKeyFunctionSet.add((HotKeyFunction) functionClass.newInstance());
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
        return hotKeyFunctionSet;
    }
}
