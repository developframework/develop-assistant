package com.github.develop.assistant.function;

import com.github.develop.assistant.*;
import com.github.develop.assistant.window.settings.SettingsWindow;
import com.melloware.jintellitype.JIntellitype;
import lombok.Getter;

import java.awt.*;

/**
 * 设置菜单热键
 */
public class SettingsWindowFunction implements HotKeyFunction, ApplicationAware, MenuSupport {

    @Getter
    private SettingsWindow settingsWindow;

    @Override
    public void setApplication(Application application) {
        settingsWindow = new SettingsWindow(application);
        settingsWindow.setVisible(true);
    }

    @Override
    public HotKey hotKey(int identifier) {
        return new HotKey(identifier, JIntellitype.MOD_ALT, 'P', "设置菜单");
    }

    @Override
    public void event() {
        settingsWindow.toggle();
    }

    @Override
    public MenuItem createMenuItem() {
        MenuItem settings = new MenuItem("设置菜单");
        settings.addActionListener(event -> settingsWindow.toggle());
        return settings;
    }

}
