package com.github.develop.assistant;

import com.github.develop.assistant.window.settings.SettingsWindow;
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

    private PopupMenu createMenu() {
        PopupMenu menu = new PopupMenu();

        for(HotKeyFunction function : application.hotKeyFunctions()) {
            MenuItem menuItem = function.createMenuItem();
            if (menuItem != null) {
                menu.add(menuItem);
            }
        }
        return menu;
    }
}
