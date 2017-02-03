package com.github.develop.assistant;

import com.github.develop.assistant.function.SettingsWindowFunction;
import lombok.Getter;

import java.awt.*;
import java.io.File;
import java.util.List;
import java.util.Set;

public class DevelopAssistantApplication implements Application {

    @Getter
    private HotKeyManager hotKeyManager;

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
        SettingsWindowFunction settingsWindowFunction = new SettingsWindowFunction(this);
        hotKeyManager.registerHotKey(settingsWindowFunction);
        if (SystemTray.isSupported()) {
            new Tray(this, settingsWindowFunction.getSettingsWindow());
        }

        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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
}
