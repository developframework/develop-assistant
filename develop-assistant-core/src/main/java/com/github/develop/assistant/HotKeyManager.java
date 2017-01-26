package com.github.develop.assistant;

import com.melloware.jintellitype.JIntellitype;

import java.util.HashSet;
import java.util.Set;

public class HotKeyManager {

    private Set<HotKey> hotKeys = new HashSet<>();

    private MultipleHotKeyListener multipleHotKeyListener = new MultipleHotKeyListener();

    private int identifier = 1;

    public HotKeyManager() {
        JIntellitype.getInstance().addHotKeyListener(multipleHotKeyListener);
    }

    public void addHotKey(HotKeyFunction hotKeyFunction) {
        HotKey hotKey = hotKeyFunction.hotKey(identifier);
        hotKeys.add(hotKey);
        multipleHotKeyListener.addEvent(identifier++, hotKeyFunction);
        System.out.printf("%s\t%s\n", hotKey.toString(), hotKeyFunction.getClass().getSimpleName());
    }

    public void register() {
        JIntellitype jIntellitype = JIntellitype.getInstance();
        for (HotKey hotKey : hotKeys) {
            jIntellitype.registerHotKey(hotKey.getIdentifier(), hotKey.getModifier(), hotKey.getKeycode());
        }
    }

    public void removeAllHotKeys() {
        JIntellitype.getInstance().cleanUp();
    }
}
