package com.github.develop.assistant;

import com.melloware.jintellitype.HotkeyListener;

import java.util.HashMap;
import java.util.Map;

public class MultipleHotKeyListener implements HotkeyListener{

    private Map<Integer, HotKeyFunction> hotKeyFunctions = new HashMap<>();

    public void addEvent(int identifier, HotKeyFunction hotKeyFunction) {
        hotKeyFunctions.put(identifier, hotKeyFunction);
    }

    @Override
    public void onHotKey(int identifier) {
        hotKeyFunctions.get(identifier).event();
    }
}
