package com.github.develop.assistant;

import com.melloware.jintellitype.JIntellitype;

import java.util.HashSet;
import java.util.Set;

public class HotKeyManager {

    private Set<HotKey> hotKeys = new HashSet<>();

    private MultipleHotKeyListener multipleHotKeyListener = new MultipleHotKeyListener();

    public void addHotKey(HotKeyFunction hotKeyFunction) {
        hotKeys.add(hotKeyFunction.hotKey());
        multipleHotKeyListener.addEvent(hotKeyFunction);
    }

    public void register() {
        JIntellitype jIntellitype = JIntellitype.getInstance();
        jIntellitype.addHotKeyListener(multipleHotKeyListener);

        for(HotKey hotKey : hotKeys) {
            jIntellitype.registerHotKey(hotKey.getIdentifier(), hotKey.getModifier(), hotKey.getKeycode());
        }
    }

    public void removeAllHotKeys() {
        JIntellitype.getInstance().cleanUp();
    }
}
