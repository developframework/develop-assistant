package com.github.develop.assistant;

import com.melloware.jintellitype.JIntellitype;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class HotKeyManager {

    @Getter
    private MultipleHotKeyListener multipleHotKeyListener;

    @Getter
    private List<HotKey> hotKeys = new ArrayList<>();

    private static int identifier = 1;

    public HotKeyManager(Application application) {
        this.multipleHotKeyListener = new MultipleHotKeyListener(application);
        JIntellitype.setLibraryLocation(System.getProperty("user.dir"));
        JIntellitype.getInstance().addHotKeyListener(multipleHotKeyListener);
    }

    /**
     * 注册快捷键
     * @param hotKeyFunction
     */
    public void registerHotKey(HotKeyFunction hotKeyFunction) {
        HotKey hotKey = hotKeyFunction.hotKey(identifier);
        hotKeys.add(hotKey);
        JIntellitype.getInstance().registerHotKey(hotKey.getIdentifier(), hotKey.getModifier(), hotKey.getKeycode());
        multipleHotKeyListener.addEvent(identifier++, hotKeyFunction);
    }

    /**
     * 注销快捷键
     * @param identifier
     */
    public void unregisterHotKey(int identifier) {
        JIntellitype.getInstance().unregisterHotKey(identifier);
        multipleHotKeyListener.removeEvent(identifier);
    }

    /**
     * 注销全部快捷键
     */
    public void removeAllHotKeys() {
        JIntellitype.getInstance().cleanUp();
        identifier = 1;
    }
}
