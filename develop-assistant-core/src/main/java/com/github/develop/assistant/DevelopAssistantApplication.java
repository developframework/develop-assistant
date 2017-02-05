package com.github.develop.assistant;

import com.github.develop.assistant.function.ExitApplicationFunction;
import com.github.develop.assistant.function.SettingsWindowFunction;
import lombok.Getter;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DevelopAssistantApplication implements Application {

    @Getter
    private HotKeyManager hotKeyManager;

    private Tray tray;

    public static void main(String[] args) {
        DevelopAssistantApplication app = new DevelopAssistantApplication();
        app.start();
    }

    @Override
    public synchronized void start() {
        String path = System.getProperty("user.dir") + File.separator + "function";
        FunctionLoader loader = FunctionLoaderFactory.functionLoader(path);
        hotKeyManager = new HotKeyManager(this);
        Set<HotKeyFunction> set = loader.load();
        for (HotKeyFunction function : set) {
            hotKeyManager.registerHotKey(function);
        }

        registerDefaultHotKeyFunction();

        if (SystemTray.isSupported()) {
            tray = new Tray(this);
        }

        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void registerDefaultHotKeyFunction() {
        hotKeyManager.registerHotKey(new SettingsWindowFunction(this));
        hotKeyManager.registerHotKey(new ExitApplicationFunction(this));
    }

    @Override
    public synchronized void destroy() {
        hotKeyManager.removeAllHotKeys();
        System.exit(0);
    }

    @Override
    public List<HotKey> hotKeys() {
        return hotKeyManager.getHotKeys();
    }

    @Override
    public List<HotKeyFunction> hotKeyFunctions() {
        return new ArrayList<>(hotKeyManager.getMultipleHotKeyListener().getHotKeyFunctions().values());
    }

    @Override
    public PopupMenu trayPopupMenu() {
        return tray.getPopupMenu();
    }
}
