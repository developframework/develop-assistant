package com.github.develop.assistant.function;

import com.github.develop.assistant.Application;
import com.github.develop.assistant.HotKey;
import com.github.develop.assistant.HotKeyFunction;
import com.github.develop.assistant.window.settings.SettingsWindow;
import com.melloware.jintellitype.JIntellitype;
import lombok.Getter;

/**
 * 设置菜单热键
 */
public class SettingsWindowFunction implements HotKeyFunction{

    @Getter
    private SettingsWindow settingsWindow;

    public SettingsWindowFunction(Application application) {
        settingsWindow = new SettingsWindow(application);
        settingsWindow.setVisible(true);
    }

    @Override
    public HotKey hotKey(int identifier) {
        return new HotKey(identifier, JIntellitype.MOD_ALT, 'P', "设置菜单");
    }

    @Override
    public void event(Application application) {
        settingsWindow.toggle();
    }
}
