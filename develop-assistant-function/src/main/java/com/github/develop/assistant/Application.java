package com.github.develop.assistant;

import java.util.List;

public interface Application {

    void start();

    void destroy();

    List<HotKey> hotKeys();
}
