package com.github.develop.assistant;

import com.github.develop.assistant.resource.Resource;

import java.awt.*;
import java.util.List;

public interface Application {

    void start();

    void destroy();

    List<HotKey> hotKeys();

    List<HotKeyFunction> hotKeyFunctions();

    PopupMenu trayPopupMenu();

    Resource getResource(String name);

    Resource putResource(String name, Resource resource);
}
