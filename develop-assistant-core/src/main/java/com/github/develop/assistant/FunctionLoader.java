package com.github.develop.assistant;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

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

    public List<FunctionHandlers> load() {
        final List<FunctionHandlers> functionHandlers = new ArrayList<>();
        for (String jarPath : jarPaths) {
            try {
                URL url = new URL("file:" + dir + File.separator + jarPath);
                final URLClassLoader urlClassLoader = new URLClassLoader(new URL[]{url}, Thread.currentThread().getContextClassLoader());
                String json = loadJSON(urlClassLoader, url);
                functionHandlers.add(parse(urlClassLoader, json));
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        return functionHandlers;
    }

    private String loadJSON(URLClassLoader urlClassLoader, URL url) {
        InputStream is = urlClassLoader.getResourceAsStream("function.handlers");
        if (is == null) {
            throw new RuntimeException("Not found function.handlers in jar: " + url.toString());
        }
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        br.lines().forEach(stringBuilder::append);
        try {
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    private FunctionHandlers parse(URLClassLoader urlClassLoader, String json) {
        FunctionHandlers functionHandlers = new FunctionHandlers();
        JSONObject root = JSONObject.parseObject(json);
        JSONArray functions = root.getJSONArray("functions");
        functions.forEach(functionClassName -> {
            try {

                Class<?> clazz = urlClassLoader.loadClass((String) functionClassName);
                if (HotKeyFunction.class.isAssignableFrom(clazz)) {
                    functionHandlers.getFunctions().add((HotKeyFunction) clazz.newInstance());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        if (root.containsKey("settings_tabs")) {
            JSONArray tabs = root.getJSONArray("settings_tabs");
            tabs.forEach(tabsClassName -> {
                try {
                    Class<?> clazz = Class.forName((String) tabsClassName);
                    if (SettingsTab.class.isAssignableFrom(clazz)) {
                        functionHandlers.getSettingsTabs().add((SettingsTab) clazz.newInstance());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
        return functionHandlers;
    }
}
