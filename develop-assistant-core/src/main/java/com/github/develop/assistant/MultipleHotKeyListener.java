package com.github.develop.assistant;

import com.melloware.jintellitype.HotkeyListener;

import java.util.HashSet;
import java.util.Set;

public class MultipleHotKeyListener implements HotkeyListener{

    private Set<HotKeyFunction> hotKeyFunctions = new HashSet<>();

    public void addEvent(HotKeyFunction hotKeyFunction) {
        hotKeyFunctions.add(hotKeyFunction);
    }

    @Override
    public void onHotKey(int i) {
        ClipboardContext clipboardContext = new ClipboardContext();
        for(HotKeyFunction hotKeyFunction : hotKeyFunctions) {
            if(hotKeyFunction.hotKey().getIdentifier() == i) {
                hotKeyFunction.event(clipboardContext);
                break;
            }
        }
    }
}
