package com.github.develop.assistant.window.settings;

import com.github.develop.assistant.Application;
import com.github.develop.assistant.SettingsTab;

import javax.swing.*;
import com.github.develop.assistant.Mnemonic;

import java.awt.event.KeyEvent;

/**
 * 快捷键设置页卡
 */
public class HotKeysSettingsTab implements SettingsTab{
    @Override
    public String title() {
        return "快捷键设置";
    }

    @Override
    public JPanel panel(Application application) {
        return new HotKeysSettingsPanel(application);
    }

    @Override
    public Mnemonic mnemonic() {
        return new Mnemonic(0, KeyEvent.VK_H, 0);
    }

    @Override
    public void save() {

    }
}
