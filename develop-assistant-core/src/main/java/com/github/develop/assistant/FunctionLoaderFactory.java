package com.github.develop.assistant;

import java.io.File;
import java.util.stream.Stream;

public class FunctionLoaderFactory {

    public static FunctionLoader functionLoader(String path) {
        String[] files = files(path);
        Stream.of(files).forEach(System.out::println);
       return new FunctionLoader(path, files);
    }

    private static String[] files(String path) {
        File file = new File(path);
        if (file.isDirectory()) {
            return file.list((dir, name) -> name.endsWith(".jar"));
        }
        return new String[0];
    }
}
