package com.github.develop.assistant;

import com.melloware.jintellitype.HotkeyListener;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

public class MultipleHotKeyListener implements HotkeyListener{

    @Getter
    private Map<Integer, HotKeyFunction> hotKeyFunctions = new HashMap<>();

    private Application application;

    public MultipleHotKeyListener(Application application) {
        this.application = application;
    }

    public void addEvent(int identifier, HotKeyFunction hotKeyFunction) {
        hotKeyFunctions.put(identifier, hotKeyFunction);
    }

    public void removeEvent(int identifier) {
        hotKeyFunctions.remove(identifier);
    }

    @Override
    public void onHotKey(int identifier) {
        hotKeyFunctions.get(identifier).event(application);
    }
}
