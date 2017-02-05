package com.github.develop.assistant;

import java.awt.*;

/**
 * 热键事件接口
 */
public interface HotKeyFunction {

    HotKey hotKey(int identifier);

    void event(Application application);

    MenuItem createMenuItem();
}
