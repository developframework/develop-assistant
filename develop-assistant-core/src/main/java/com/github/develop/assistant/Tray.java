package com.github.develop.assistant;

import java.awt.*;

/**
 * 系统托盘
 */
public class Tray {

    private TrayIcon trayIcon;
    private HotKeyManager hotKeyManager;

    public Tray(HotKeyManager hotKeyManager) {
        this.hotKeyManager = hotKeyManager;
        SystemTray systemTray = SystemTray.getSystemTray();
        Image image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/image/Files.png"));
        trayIcon = new TrayIcon(image, "DevelopAssistant", createMenu());
        try {
            systemTray.add(trayIcon);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    private PopupMenu createMenu() {
        PopupMenu menu = new PopupMenu();

        MenuItem exit = new MenuItem("exit");
        exit.addActionListener(event -> System.exit(0));
        menu.add(exit);

        MenuItem stop = new MenuItem("stop");
        stop.addActionListener(event -> hotKeyManager.removeAllHotKeys());
        menu.add(stop);

        return menu;
    }
}
