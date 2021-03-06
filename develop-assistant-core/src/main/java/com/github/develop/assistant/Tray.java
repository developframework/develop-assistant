package com.github.develop.assistant;

import lombok.Getter;

import java.awt.*;

/**
 * 系统托盘
 */
public class Tray {

    private TrayIcon trayIcon;
    private DevelopAssistantApplication application;
    @Getter
    private PopupMenu popupMenu;

    public Tray(DevelopAssistantApplication application) {
        this.application = application;
        SystemTray systemTray = SystemTray.getSystemTray();
        Image image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/image/tray.png"));
        popupMenu = createMenu();
        trayIcon = new TrayIcon(image, "develop-assistant", popupMenu);
        try {
            systemTray.add(trayIcon);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    public void destroy() {
        SystemTray systemTray = SystemTray.getSystemTray();
        systemTray.remove(trayIcon);
    }

    private PopupMenu createMenu() {
        final PopupMenu menu = new PopupMenu();
        application.hotKeyFunctions().stream().filter(function -> function instanceof MenuSupport).forEach(function -> menu.add(((MenuSupport) function).createMenuItem()));
        return menu;
    }
}
