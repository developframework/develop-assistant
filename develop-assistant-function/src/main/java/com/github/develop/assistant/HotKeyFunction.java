package com.github.develop.assistant;

/**
 * Created by Administrator on 2017/1/24.
 */
public interface HotKeyFunction {

    HotKey hotKey();

    void event(ClipboardContext clipboardContext);
}
