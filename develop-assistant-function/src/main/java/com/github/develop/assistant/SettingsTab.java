package com.github.develop.assistant;

import javax.swing.*;

/**
 * 设置面板页卡
 */
public interface SettingsTab {

    String title();

    JPanel panel(Application application);

    Mnemonic mnemonic();

    void save();

}
