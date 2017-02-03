package com.github.develop.assistant;

import com.github.develop.assistant.window.settings.SettingsWindow;

import java.awt.*;

/**
 * 系统托盘
 */
public class Tray {

    private TrayIcon trayIcon;
    private DevelopAssistantApplication application;
    private SettingsWindow settingsWindow;

    public Tray(DevelopAssistantApplication application, SettingsWindow settingsWindow) {
        this.application = application;
        this.settingsWindow = settingsWindow;
        SystemTray systemTray = SystemTray.getSystemTray();
        Image image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/image/icon.png"));
        trayIcon = new TrayIcon(image, "DevelopAssistant", createMenu());
        try {
            systemTray.add(trayIcon);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    private PopupMenu createMenu() {
        PopupMenu menu = new PopupMenu();

        MenuItem settings = new MenuItem("设置菜单");
        settings.addActionListener(event -> settingsWindow.toggle());
        menu.add(settings);

        MenuItem exit = new MenuItem("退出");
        exit.addActionListener(event -> application.destroy());
        menu.add(exit);

        return menu;
    }
}
