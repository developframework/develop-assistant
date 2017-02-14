package com.github.develop.assistant.window.settings;

import com.github.develop.assistant.Application;
import com.github.develop.assistant.BaseWindow;
import com.github.develop.assistant.Mnemonic;
import com.github.develop.assistant.SettingsTab;

import javax.swing.*;

/**
 * 设置窗口
 */
public class SettingsWindow extends BaseWindow {

    private JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.LEFT);

    public SettingsWindow(Application application) {
        super(application, "设置", 55, 50, true);
        this.setResizable(false);
        this.setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        addTab(new HotKeysSettingsTab());

        this.add(tabbedPane);

        this.add(new JSeparator());
        this.add(Box.createVerticalStrut(5));
        this.add(confirmPanel());
        this.add(Box.createVerticalStrut(5));
    }

    public void addTab(SettingsTab settingsTab) {
        tabbedPane.addTab(settingsTab.title(), settingsTab.panel(application));
        Mnemonic mnemonic = settingsTab.mnemonic();
        if (mnemonic != null) {
            tabbedPane.setMnemonicAt(mnemonic.getTabIndex(), mnemonic.getKey());
            tabbedPane.setDisplayedMnemonicIndexAt(mnemonic.getTabIndex(), mnemonic.getMnemonicIndex());
        }
    }

    private JPanel confirmPanel() {
        JPanel confirmPanel = new JPanel();
        BoxLayout boxLayout = new BoxLayout(confirmPanel, BoxLayout.X_AXIS);
        confirmPanel.setLayout(boxLayout);
        confirmPanel.add(Box.createGlue());
        JButton confirm = new JButton("保存");
        confirmPanel.add(confirm);
        confirmPanel.add(Box.createHorizontalStrut(10));
        return confirmPanel;
    }

}
