package com.github.develop.assistant;

import java.io.File;

public class FunctionLoaderFactory {

    public static FunctionLoader functionLoader(String path) {
       return new FunctionLoader(path, files(path));
    }

    private static String[] files(String path) {
        File file = new File(path);
        if (file.isDirectory()) {
            return file.list((dir, name) -> name.endsWith(".jar"));
        }
        return new String[0];
    }
}
